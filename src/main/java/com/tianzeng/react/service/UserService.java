package com.tianzeng.react.service;


import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.moudel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaenry on 2016/6/17.
 * UserService
 */
@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User save(User entity) throws Exception {
        return userRepository.save(entity);
    }

    public void delete(Long id) throws Exception {
        userRepository.delete(id);
    }

    public void delete(User entity) throws Exception {
        userRepository.delete(entity);
    }

    public User findById(Long id) {
        return userRepository.findOne(id);
    }



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }





}
