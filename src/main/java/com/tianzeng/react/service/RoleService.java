package com.tianzeng.react.service;

import com.tianzeng.react.dao.RolesRepository;
import com.tianzeng.react.moudel.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tianzeng on 17-4-26.
 */
@Service
public class RoleService {
    @Autowired
    private RolesRepository rolesRepository;

    public Role findByDescription(String name){
        return rolesRepository.findByDescription(name);
    }
}
