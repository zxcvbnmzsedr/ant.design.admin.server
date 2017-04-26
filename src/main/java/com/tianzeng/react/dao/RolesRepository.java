package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.Role;
import com.tianzeng.react.moudel.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by tianzeng on 17-4-22.
 */
@RepositoryRestResource
public interface RolesRepository extends JpaRepository<Role,Long>{
    Role findByDescription(String name);
}
