package com.tianzeng.react.filter;

import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.config.exception.MyException;
import com.tianzeng.react.moudel.Source;
import com.tianzeng.react.moudel.TokenModel;
import com.tianzeng.react.moudel.User;
import com.tianzeng.react.service.SourceService;
import com.tianzeng.react.service.TokenService;
import com.tianzeng.react.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by tianzeng on 17-4-22.
 */
@WebFilter(filterName = "permissionFilter", urlPatterns = "/*")
public class PermissionFilter implements Filter{
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private SourceService sourceService;
    private Logger logger = Logger.getLogger(getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("进入过滤器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求的URI
        String requestURI = request.getRequestURI();
        // 不需要登录就能访问的网址
        String pattern = "/user/.*";

        if(Pattern.matches(pattern, requestURI)){
            logger.info(requestURI+"无需登录");
        }

        // 检测用户登录
        String token = request.getHeader("token");
        // 检查用户是否登录
        TokenModel tokenModel = tokenService.checkToken(token);
        if(tokenModel == null){
            logger.info("用户没登录");
        }

        // 检测资源是否存在
        Source source = sourceService.findByName(requestURI);
        if(source != null){
            logger.info(requestURI+"存在");
        }

        // 检查用户的角色是否能够访问这个资源


    }

    @Override
    public void destroy() {

    }
}
