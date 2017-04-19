package com.tianzeng.react.dao;


import com.tianzeng.react.moudel.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by tianzeng on 2017-03-27.
 */
@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findByName(String username);
}
