package com.yws.security.filter;

import com.yws.account.dto.SysResource;
import com.yws.account.dto.SysRole;
import com.yws.account.mapper.SysResourceMapper;
import com.yws.account.mapper.SysRoleMapper;
import com.yws.account.mapper.SysRoleResourceMapper;
import com.yws.security.tool.AntUrlPathMatcher;
import com.yws.security.tool.UrlMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.*;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/21 20:23
 * @description
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysResourceMapper resourceMapper;

    private UrlMatcher urlMatcher = new AntUrlPathMatcher();
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;



    //tomcat启动时实例化一次
    public MyInvocationSecurityMetadataSource() {
//        loadResourceDefine();
    }

    /**
     * oadResourceDefine方法不是必须的，这个只是加载所有的资源与权限的对应关系并缓存起来
     */
//    private void loadResourceDefine() {
//        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
//        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//        resourceMap.put("/index.jsp", atts);
//        Collection<ConfigAttribute> attsno =new ArrayList<ConfigAttribute>();
//        ConfigAttribute cano = new SecurityConfig("ROLE_ADMIN");
//        attsno.add(cano);
//        resourceMap.put("/admin.jsp", attsno);
//
//        Collection<ConfigAttribute> loginDo =new ArrayList<ConfigAttribute>();
//        ConfigAttribute loginC = new SecurityConfig("ROLE_ADMIN");
//        loginDo.add(loginC);
//        resourceMap.put("/index", loginDo);
//    }

    /**
     *getAttributes根据参数（被拦截url）返回权限集合    使用数据库时在该方法获取相应权限数据，在比较
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 将参数转为url
        String url = ((FilterInvocation)object).getRequestUrl();
        int index = url.indexOf("?");
        if(index != -1) {
            url = url.substring(0, index);
        }
        getauthResourceMap();
        Iterator<String> item = resourceMap.keySet().iterator();
        while (item.hasNext()) {
            String resURL = item.next();
            if (urlMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }
    @Override
    public boolean supports(Class<?>clazz) {
        return true;
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    private void getauthResourceMap(){
        resourceMap = new HashMap<>();
        List<SysRole> roleList = roleMapper.getAll();
        roleList.forEach(sysRole -> {
            ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + sysRole.getRoleName());
            List<SysResource> resourceList = resourceMapper.getByRoleId(sysRole.getRoleId());
            resourceList.forEach(sysResource -> {
                String url = sysResource.getUrl();
                if(resourceMap.containsKey(url)) {
                    Collection<ConfigAttribute> value = resourceMap.get(url);
                    value.add(configAttribute);
                    resourceMap.put(url,value);
                } else {
                    Collection<ConfigAttribute> value = new ArrayList<>();
                    value.add(configAttribute);
                    resourceMap.put(url, value);
                }
            });
        });

//        resourceMap = new HashMap<>();
//        List<SysAuthorities> authoritiesList = authoritiesMapper.selectAll();
//        authoritiesList.forEach(authority -> {
//            ConfigAttribute configAttribute = new SecurityConfig(authority.getAuthorityTitle());
//            SysAuthoritiesResource authoritiesResource = new SysAuthoritiesResource();
//            authoritiesResource.setAuthorityId(authority.getAuthorityId());
//            List<SysAuthoritiesResource> authoritiesResourceList = authoritiesResourceMapper.select(authoritiesResource);
//            authoritiesResourceList.forEach(authRes -> {
//                String url = authRes.getUrl();
//                if(resourceMap.containsKey(url)) {
//                    Collection<ConfigAttribute> value = resourceMap.get(url);
//                    value.add(configAttribute);
//                    resourceMap.put(url,value);
//                } else {
//                    Collection<ConfigAttribute> value = new ArrayList<>();
//                    value.add(configAttribute);
//                    resourceMap.put(url, value);
//                }
//            });
//        });
    }
}
