package com.tianzeng.react.security.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import com.tianzeng.react.common.Config;
import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by tianzeng on 17-4-22.
 */
@WebFilter(filterName = "permissionFilter", urlPatterns = "/*")
public class PermissionFilter implements Filter{
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("开始检测权限");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        for (String url: Config.URUSET) {
            if(url.startsWith(request.getRequestURI())){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        Assert.notNull(request.getHeader("access-token"),"验证参数错误");
        String[] accessToken = request.getHeader("access-token").split(",");
        userService.check(Long.parseLong(accessToken[0]),accessToken[1],request.getMethod(), request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);

    }



    @Override
    public void destroy() {

    }
}
