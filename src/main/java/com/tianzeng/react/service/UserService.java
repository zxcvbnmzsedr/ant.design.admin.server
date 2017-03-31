package com.tianzeng.react.service;

import com.tianzeng.react.dao.UserDao;
import com.tianzeng.react.moudel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * Created by tianzeng on 2017-03-27.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findUser(User user){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.DEFAULT);

        Example<User> example = Example.of(user,matcher);

        return userDao.findOne(example);
    }

    public void saveOne(User user) {

        userDao.save(user);
    }
}
