package com.tianzeng.react.moudel;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by tianzeng on 2017-03-16.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    @Column
    private String password;

    @OneToOne
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    private StaffDataMoudel staffDataMoudel;

    public StaffDataMoudel getStaffDataMoudel() {
        return staffDataMoudel;
    }

    public void setStaffDataMoudel(StaffDataMoudel staffDataMoudel) {
        this.staffDataMoudel = staffDataMoudel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
