package com.tianzeng.react.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.moudel.*;
import com.tianzeng.react.service.PermissionService;
import com.tianzeng.react.service.RoleService;
import com.tianzeng.react.service.SourceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianzeng on 17-5-2.
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SourceService sourceService;
    @GetMapping
    public Result sources(){
        Result result = new Result();
        List<Role> sourceList = roleService.findAll();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Permission.class,"id","description");
        Assert.notNull(sourceList,"角色列表为空");
        logger.info(JSON.toJSONString(sourceList,filter));
        result.setObj(JSONObject.parse(JSON.toJSONString(sourceList,filter)));
        return result;
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Long id,@RequestBody Role role){
        Result result = new Result();
        List<String> perssionList = role.getPermission(); // 这是角色需要的权限
        List<Permission> permissionList = new ArrayList<>();
        List<Permission> allList = new ArrayList<>();
        role.setRoleId(id);
        for(String per:perssionList){
            if(per.split("-").length == 1){
                String source = per.split("-")[0]; // 资源ID
                permissionList = permissionService.findBySourceId(Long.valueOf(source));
            }


            if(per.split("-").length == 2){
                String persion = per.split("-")[1]; // 权限ID
                permissionList.add(permissionService.findOne(Long.valueOf(persion)));
            }
            allList.addAll(permissionList);

        }
        role.setPermissions(allList);
        roleService.update(role);


        return result;

    }
}
