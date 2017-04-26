package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by kaenry on 2016/6/17.
 * UserRepo
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String userNamelo);
}
