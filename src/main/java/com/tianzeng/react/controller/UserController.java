package com.tianzeng.react.controller;

import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by tianzeng on 2017/5/19.
 */
@RepositoryRestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PutMapping(value = "/users")
    public @ResponseBody ResponseEntity<?> getProducers() {
        List<User> producers = userRepository.findAll();

        Resources<User> resources = new Resources<User>(producers);


        // add other links as needed

        return ResponseEntity.ok(resources);
    }
}
