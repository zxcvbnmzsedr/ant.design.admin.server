package com.tianzeng.react.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tianzeng on 17-4-19.
 */
public class KanBanPreAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    public static final String SSO_TOKEN = "X-Auth-Token";
    public static final String SSO_CREDENTIALS = "N/A";

    public KanBanPreAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(SSO_TOKEN);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return SSO_CREDENTIALS;
    }
}
