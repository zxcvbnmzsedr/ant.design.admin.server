package com.tianzeng.react.moudel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    private String name;

    private String status;// 角色启用状态

    private String description; //角色描述
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
    private List<Permission> permissions; // 角色所拥有的权限

    @Transient
    private List<String> permission;  // 用于接受前台传来的权限字符
}
