package com.tianzeng.react.moudel;

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
public class Role {
    @Id
    @GeneratedValue
    private Long roleId;

    private String name;

    private String status;// 角色启用状态

    private String description; //角色描述
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    @OneToMany
    private Set<Permission> permissions = new HashSet<>(); // 角色所拥有的权限
}
