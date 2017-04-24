package com.tianzeng.react.dao;

import com.tianzeng.react.moudel.Role;
import com.tianzeng.react.moudel.Source;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tianzeng on 17-4-22.
 */
public interface RolesRepository extends JpaRepository<Role,Long>{
}
