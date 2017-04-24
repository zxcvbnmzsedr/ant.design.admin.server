package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.Permission;
import com.tianzeng.react.moudel.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tianzeng on 17-4-22.
 */
public interface PermissionRepository extends JpaRepository<Permission,Long>{
}
