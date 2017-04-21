package com.tianzeng.react.moudel;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

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
    private Long id;

    private String username;

    private String password;

    private String role;

}
