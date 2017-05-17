package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tianzeng on 2017/5/17.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String userName);
}
