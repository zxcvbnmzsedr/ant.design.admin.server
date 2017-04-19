package com.tianzeng.react.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tianzeng on 17-4-19.
 * spring-security 配置类
 */
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers("/tokens/**", "/main.css").permitAll().
                anyRequest().
                authenticated().and().
                exceptionHandling().
                authenticationEntryPoint(new RestAuthenticationEntryPoint());
        http.addFilter(headerAuthenticationFilter());
    }
    @Component
    public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException ) throws IOException {
            response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
        }
    }
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(preAuthenticationProvider());
    }

    private AuthenticationProvider preAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new KanBanAuthenticationUserDetailsService());

        return provider;
    }


    @Bean
    public KanBanPreAuthenticationFilter headerAuthenticationFilter() throws Exception {
        return new KanBanPreAuthenticationFilter(authenticationManager());
    }
}
