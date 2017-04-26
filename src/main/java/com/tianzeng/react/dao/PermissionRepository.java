package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.Permission;
import com.tianzeng.react.moudel.Role;
import com.tianzeng.react.moudel.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by tianzeng on 17-4-22.
 */
@RepositoryRestResource
public interface PermissionRepository extends JpaRepository<Permission,Long>{
    List<Permission> findBySourceId(Long sourceId);
}
