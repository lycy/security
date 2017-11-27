package com.yws.security.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/21 20:09
 * @description
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    //配置文件注入
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }


    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用 MyInvocationSecurityMetadataSource 的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

//    登陆后，每次访问资源都会被这个拦截器拦截，会执行doFilter这个方法，这个方法调用了invoke方法，
//    其中fi断点显示是一个url（可能重写了toString方法吧，但是里面还有一些方法的），
//    最重要的是beforeInvocation这个方法，它首先会调用 MyInvocationSecurityMetadataSource 类的getAttributes方法获取被拦截url所需的权限，
//    在调用MyAccessDecisionManager类decide方法判断用户是否够权限。弄完这一切就会执行下一个拦截器



    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
    @Override
    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }


    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource newSource) {
        this.securityMetadataSource = newSource;
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }


}
