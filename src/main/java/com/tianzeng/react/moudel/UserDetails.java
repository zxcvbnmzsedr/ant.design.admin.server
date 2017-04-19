package com.tianzeng.react.moudel;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by tianzeng on 17-4-19.
 */
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{

    public User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
