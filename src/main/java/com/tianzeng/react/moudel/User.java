package com.tianzeng.react.moudel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * Created by tianzeng on 17-4-19.
 */
@Data
@Entity
public class User{
    private @Id @GeneratedValue Long id;

    private String name;

    private @JsonIgnore String password;

    private String[] roles;

    private Boolean enabled;

}
