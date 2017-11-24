package com.yws.security.filter;

import com.yws.security.tool.AntUrlPathMatcher;
import com.yws.security.tool.UrlMatcher;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.*;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/21 20:23
 * @description
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    //tomcat启动时实例化一次
    public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    /**
     * oadResourceDefine方法不是必须的，这个只是加载所有的资源与权限的对应关系并缓存起来
     */
    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//        ConfigAttribute ca = new SecurityConfig("ROLE_USER");
//        ConfigAttribute caAdmin = new SecurityConfig("ROLE_ADMIN");
//        atts.add(ca);
//        atts.add(caAdmin);
        resourceMap.put("/index.jsp", atts);
        Collection<ConfigAttribute> attsno =new ArrayList<ConfigAttribute>();
        ConfigAttribute cano = new SecurityConfig("ROLE_ADMIN");
        attsno.add(cano);
        resourceMap.put("/admin.jsp", attsno);

        Collection<ConfigAttribute> loginDo =new ArrayList<ConfigAttribute>();
        ConfigAttribute loginC = new SecurityConfig("ROLE_ADMIN");
        loginDo.add(loginC);
        resourceMap.put("/index", loginDo);
    }

    /**
     *getAttributes根据参数（被拦截url）返回权限集合    使用数据库时在该方法获取相应权限数据，在比较
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 将参数转为url
        String url = ((FilterInvocation)object).getRequestUrl();
        Collection<ConfigAttribute> cas = new ArrayList<ConfigAttribute>();
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    public boolean supports(Class<?>clazz) {
        return true;
    }
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}
