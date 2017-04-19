package com.tianzeng.react.config.security;

import com.tianzeng.react.moudel.User;
import com.tianzeng.react.moudel.UserDetails;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;

/**
 * Created by tianzeng on 17-4-19.
 */
public class KanBanAuthenticationUserDetailsService
        implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        String principal = (String) token.getPrincipal();

        if(!StringUtils.isEmpty(principal)) {

            return new UserDetails();
        }

        return null;
    }
}
