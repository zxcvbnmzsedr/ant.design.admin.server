package com.tianzeng.react.service;

import com.tianzeng.react.dao.UserRepository;
import com.tianzeng.react.moudel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by tianzeng on 17-4-19.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByName(String username, String password) {
        return userRepository.findByName(username);
    }
}
