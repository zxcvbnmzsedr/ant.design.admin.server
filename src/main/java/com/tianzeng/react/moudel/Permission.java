package com.tianzeng.react.moudel;

import com.tianzeng.react.enums.SourcePermissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianzeng on 17-4-22.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description; // 权限描述

    @OneToMany
    private Set<Source> source = new HashSet<>();//该权限对应的资源，也就是Source的某一条记录的唯一标识

    @Enumerated(EnumType.STRING)
    private SourcePermissions permissions; // 一种资源对应有四种权限，分别对这种资源的browse、create、update、delete


}
