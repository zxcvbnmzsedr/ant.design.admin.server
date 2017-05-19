package com.tianzeng.react.dao;

import com.tianzeng.react.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by tianzeng on 2017/5/19.
 */
@RepositoryRestResource(excerptProjection=Role.class)
public interface RoleRepository extends JpaRepository<Role,Long>{

}
