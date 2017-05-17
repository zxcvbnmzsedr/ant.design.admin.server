package com.tianzeng.react.moudel;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by kaenry on 2016/6/17.
 * User
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "sec_user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String providerName;

    private String email;

    private String mobile;

    private String password;

    private String avatarUrl;

    private String firstName;

    private String lastName;

    private String fullName;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    @OneToMany
    private Set<Role> roles;
}
