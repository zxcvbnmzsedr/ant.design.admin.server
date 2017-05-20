package com.tianzeng.react.controller;

import com.tianzeng.react.common.Config;
import com.tianzeng.react.dao.PermissionRepository;
import com.tianzeng.react.dao.SourceRepository;
import com.tianzeng.react.security.Permission;
import com.tianzeng.react.security.Source;
import com.tianzeng.react.security.User;
import com.tianzeng.react.security.enums.SourcePermissions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tianzeng on 2017/5/20.
 */
@RepositoryRestController
public class SourceController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @PostMapping("/sources")
    @Transactional
    public void createSource(@RequestBody Source source){
        Source result = sourceRepository.save(source);
        // 为资源创建四种权限
        Permission[] permissions = new Permission[Config.PERSSIONS_NUMBER];
        for(int i=0;i<permissions.length;i++){
            permissions[i] = new Permission();
            permissions[i].setSource(result);
        }
        permissions[0].setMethod(SourcePermissions.BROWSE);
        permissions[0].setDescription(source.getName()+"查询");

        permissions[1].setMethod(SourcePermissions.CREATE);
        permissions[1].setDescription(source.getName()+"创建");

        permissions[2].setMethod(SourcePermissions.UPDATE);
        permissions[2].setDescription(source.getName()+"修改");

        permissions[3].setMethod(SourcePermissions.DELETE);
        permissions[3].setDescription(source.getName()+"删除");
        permissionRepository.save(Arrays.asList(permissions));
    }

    @DeleteMapping("/sources/{id}")
    public void deleteSource(@PathVariable("id")Long id){
        Source source = sourceRepository.findOne(id);
        List<Permission> permissions = permissionRepository.findBySource(source);
        logger.info(permissions);
        permissionRepository.delete(permissions);
    }
}
