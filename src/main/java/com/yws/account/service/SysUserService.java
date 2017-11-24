package com.yws.account.service;

import com.yws.account.dto.SysUser;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/24 16:07
 * @name
 * @description
 */
public interface SysUserService {
    SysUser getUserByUsername(String username);
}
