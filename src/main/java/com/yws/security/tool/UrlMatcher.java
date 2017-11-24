package com.yws.security.tool;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/21 20:28
 * @name
 * @description
 */
public interface UrlMatcher {
    Object compile(String paramString);
    boolean pathMatchesUrl(Object paramObject, String paramString);
    String getUniversalMatchPattern();
    boolean requiresLowerCaseUrl();
}
