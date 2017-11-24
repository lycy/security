package com.yws.account.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/24 15:58
 * @description
 */
public class SysRoleResource extends BaseDTO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceRoleId;
    @Column
    private Integer roleId;
    @Column
    private Integer resourceId;

    public Integer getResourceRoleId() {
        return resourceRoleId;
    }

    public SysRoleResource setResourceRoleId(Integer resourceRoleId) {
        this.resourceRoleId = resourceRoleId;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public SysRoleResource setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public SysRoleResource setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
        return this;
    }
}
