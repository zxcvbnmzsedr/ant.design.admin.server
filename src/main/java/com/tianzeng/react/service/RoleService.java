package com.tianzeng.react.service;

import com.tianzeng.react.dao.RolesRepository;
import com.tianzeng.react.moudel.Permission;
import com.tianzeng.react.moudel.Role;
import com.tianzeng.react.moudel.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Role> findAll() {
        return rolesRepository.findAll();
    }

    public void update(Role role) {
        rolesRepository.save(role);
    }
}
