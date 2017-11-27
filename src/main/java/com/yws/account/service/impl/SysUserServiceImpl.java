package com.yws.account.service.impl;

import com.yws.account.dto.SysUser;
import com.yws.account.mapper.SysUserMapper;
import com.yws.account.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/24 16:08
 * @name
 * @description
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public SysUser getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
