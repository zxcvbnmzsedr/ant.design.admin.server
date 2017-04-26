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
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String username;

    private String password;

    private String rolesDescribe;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;







}
