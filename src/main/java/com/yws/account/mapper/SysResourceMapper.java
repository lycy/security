package com.yws.account.mapper;

import com.yws.account.dto.SysResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/11/24 17:18
 * @name
 * @description
 */
@Mapper
public interface SysResourceMapper {
    /**
     * 根据角色Id查询资源数据
     * @param roleId
     * @return
     */
    List<SysResource> getByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据url查询资源数据
     * @param url
     * @return
     */
    SysResource getResourceByUri(@Param("url") String url);

}
