package com.tianzeng.react.moudel;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    private Set<Role> roles = new HashSet<>();

}
