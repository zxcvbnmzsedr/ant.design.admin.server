package com.tianzeng.react.moudel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
@Table(name = "sec_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String value;

    private String intro;

    private String pid;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "roles")
    private Set<User> users;
}
