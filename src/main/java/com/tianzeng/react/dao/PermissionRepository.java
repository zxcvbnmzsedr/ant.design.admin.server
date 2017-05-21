package com.tianzeng.react.dao;

import com.tianzeng.react.security.Permission;
import com.tianzeng.react.security.Role;
import com.tianzeng.react.security.Source;
import com.tianzeng.react.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by tianzeng on 2017/5/18.
 */
@RepositoryRestResource(excerptProjection=Permission.class)
public interface PermissionRepository extends JpaRepository<Permission,Long>{
    List<Permission> findByDescriptionStartingWith(String name);

    List<Permission> findBySource(Source source);
}
