package com.yws.securityVoter;


import com.yws.account.dto.SysResource;
import com.yws.account.dto.SysRole;
import com.yws.account.mapper.SysResourceMapper;
import com.yws.account.mapper.SysRoleMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/27 14:14
 * @description
 */
public class PermissionVoter implements AccessDecisionVoter<FilterInvocation>{

    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysRoleMapper roleMapper;


    private static final Logger logger = LoggerFactory.getLogger(PermissionVoter.class);

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
        int result = ACCESS_ABSTAIN;
        HttpServletRequest request = filterInvocation.getRequest();
        System.out.println(request.getRequestURI());
        System.out.println(request.getContextPath());
        String uri = StringUtils.substringAfter(request.getRequestURI(), request.getContextPath());
//        if (uri.startsWith("/")) {
//            uri = uri.substring(1);
//        }
        if ("".equals(uri)) {
            return ACCESS_ABSTAIN;
        }

        SysResource resource = resourceMapper.getResourceByUri(uri);
        if (resource == null) {
            return ACCESS_ABSTAIN;
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ACCESS_DENIED;
        }

        // 判断当前用户下的所有角色
        String username = authentication.getName();
        List<SysRole> roleList = roleMapper.getRoleByUsername(username);
        for(SysRole sysRole : roleList){
            List<SysResource> resourceList = resourceMapper.getByRoleId(sysRole.getRoleId());
            if (resourceList != null &&
                    resourceList.stream().map(SysResource::getResourceId).collect(Collectors.toList()).contains(resource.getResourceId()) ) {
                return ACCESS_ABSTAIN;
            }
        }
        logger.warn("access to uri :'{}' denied.", uri);
        result = ACCESS_DENIED;

        return result;
    }
}
