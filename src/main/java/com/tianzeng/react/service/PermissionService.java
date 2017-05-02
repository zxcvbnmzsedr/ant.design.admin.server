package com.tianzeng.react.service;

import com.tianzeng.react.dao.PermissionRepository;
import com.tianzeng.react.moudel.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianzeng on 17-5-2.
 */
@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findOne(Long id){
        return permissionRepository.findOne(id);
    }
    public List<Permission> findBySourceId(Long id){
        return permissionRepository.findBySourceId(id);
    }
}
