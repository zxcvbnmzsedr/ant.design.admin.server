package com.tianzeng.react.dao;

import com.tianzeng.react.security.Permission;
import com.tianzeng.react.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tianzeng on 2017/5/18.
 */
public interface PermissionRepository extends JpaRepository<Permission,Long>{
}
