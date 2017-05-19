package com.tianzeng.react.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by tianzeng on 17-4-22.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sec_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;

    private String name;

    private String status;// 角色启用状态

    private String description; //角色描述
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Permission> permissions; // 角色所拥有的权限

}
