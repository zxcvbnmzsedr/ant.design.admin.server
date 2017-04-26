package com.tianzeng.react.filter;

import com.alibaba.fastjson.JSON;
import com.tianzeng.react.config.exception.Assert;
import com.tianzeng.react.config.exception.ErrorInfo;
import com.tianzeng.react.config.exception.MyException;
import com.tianzeng.react.enums.SourcePermissions;
import com.tianzeng.react.moudel.*;
import com.tianzeng.react.service.SourceService;
import com.tianzeng.react.service.TokenService;
import com.tianzeng.react.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream writer = response.getOutputStream();
        OutputStreamWriter ow = new OutputStreamWriter(writer,"utf-8");
        String method=request.getMethod();
        // 获取请求的URI
        String requestURI = request.getRequestURI();
        // 不需要登录就能访问的网址
        String pattern = "/api/users/login";
        User user = null;
        if(Pattern.matches(pattern, requestURI)){
            logger.info(requestURI+"无需登录");
            filterChain.doFilter(request, response);
            return;
        }else {
            // 检测用户登录
            String token = request.getHeader("Access-Token");
            // 检查用户是否登录
            TokenModel tokenModel = tokenService.checkToken(token);
            if(tokenModel == null){
                Assert.throwException("token错误");
                return;
            }else {
                user = userService.findById(tokenModel.getUserId());
                if(user == null){
                    logger.info("toekn过期："+token);
                    Assert.throwException("权限不足");
                    return;
                }
            }
            // 检查用户的角色是否能够访问这个资源
            // 查询用户的角色
            List<List<Permission>> perLists = new ArrayList<>(); // 用户所有权限
            List<Role> roles = user.getRoles();
            // 查看这个用户的权限
            for(Role role:roles){
                List<Permission> permissionList = role.getPermissions();
                perLists.add(permissionList);
            }
            List<Source> sourceLists = new ArrayList<>(); // 用户所能使用的资源
            for(List<Permission> ps:perLists){
                for(Permission p:ps){
                    if(method.equals(p.getPermissions().toString())){
                        p.getPermissions().name();
                        sourceLists.add(p.getSource());
                    }
                }
            }
                for(Source p:sourceLists){
//                    p.getName().contains(requestURI)
                    String sb = p.getName();
                    sb+=".*";
                    HttpSession httpSession = request.getSession();
                    httpSession.setAttribute("user",user);
                    if(Pattern.matches(sb, requestURI)){
                        logger.info(requestURI+"请求通过");
                        filterChain.doFilter(request, response);
                        return;
                    }
                }
            }
                logger.info(requestURI+"请求失败");
                Assert.throwException("权限不足");

    }



    @Override
    public void destroy() {

    }
}
