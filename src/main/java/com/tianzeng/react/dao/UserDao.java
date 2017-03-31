package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tianzeng on 2017-03-27.
 */
public interface UserDao extends JpaRepository<User,String>{

}
