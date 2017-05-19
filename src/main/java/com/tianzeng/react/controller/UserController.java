package com.tianzeng.react.controller;

import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.dao.RoleRepository;
import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.security.Role;
import com.tianzeng.react.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tianzeng on 2017/5/19.
 */
@RepositoryRestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping(value = "/users")
    public @ResponseBody
    ResponseEntity<?> addUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) == null) {
            ArrayList<User> users = new ArrayList<>();
            Set<Role> roleSet = new HashSet<>();
            for (Role role : user.getRoles()) {
                roleSet.add(roleRepository.findByName(role.getName()));
            }
            user.setRoles(roleSet);
            users.add(userRepository.save(user));
            Resources<User> resources = new Resources<User>(users);
            return ResponseEntity.ok(resources);
        } else {
            Assert.throwException("用户名重复");
            return null;
        }
    }

    @PutMapping(value = "/users/{id}")
    public @ResponseBody
    ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id")Long id) {
        user.setUserId(id);
        ArrayList<User> users = new ArrayList<>();
        Set<Role> roleSet = new HashSet<>();
        for (Role role : user.getRoles()) {
            roleSet.add(roleRepository.findByName(role.getName()));
        }
        user.setRoles(roleSet);
        users.add(userRepository.save(user));
        Resources<User> resources = new Resources<User>(users);
        return ResponseEntity.ok(resources);
    }
}
