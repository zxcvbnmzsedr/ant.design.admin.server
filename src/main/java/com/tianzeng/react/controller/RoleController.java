package com.tianzeng.react.controller;

import com.tianzeng.react.dao.PermissionRepository;
import com.tianzeng.react.dao.RoleRepository;
import com.tianzeng.react.security.Permission;
import com.tianzeng.react.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianzeng on 2017/5/20.
 */
@RepositoryRestController
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @PostMapping("/roles")
    @Transactional
    public ResponseEntity<?> createRole(@RequestBody Role role){
        Set<Permission> permissions = new HashSet<>();
        for(Permission permission:role.getPermissions()){
            permissions.addAll(permissionRepository.findByDescriptionStartingWith(permission.getDescription()));
        }
        role.setPermissions(permissions);
        Role result = roleRepository.save(role);
        return ResponseEntity.ok(result);

    }
    @PutMapping("/roles/{id}")
    @Transactional
    public ResponseEntity<?> updateRole(@RequestBody Role role,@PathVariable("id") Long id){
        role.setRoleId(id);
        Set<Permission> permissions = new HashSet<>();
        for(Permission permission:role.getPermissions()){
            permissions.addAll(permissionRepository.findByDescriptionStartingWith(permission.getDescription()));
        }
        role.setPermissions(permissions);
        Role result = roleRepository.save(role);
        return ResponseEntity.ok(result);
    }
}
