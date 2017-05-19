package com.tianzeng.react.security;

import com.tianzeng.react.security.enums.SourcePermissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tianzeng on 17-4-22.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sec_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description; // 权限描述

    @ManyToOne(cascade=CascadeType.REMOVE)
    private Source source;//该权限对应的资源，也就是Source的某一条记录的唯一标识

    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE,mappedBy = "permissions")
    private List<Role> roles; // 角色所拥有的权限

    @Enumerated(EnumType.STRING)
    private SourcePermissions method; // 一种资源对应有四种权限，分别对这种资源的browse、create、update、delete



}
