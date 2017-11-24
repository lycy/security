package com.yws.account.dto;

import javax.persistence.*;

/**
 * @author weisen.yang@hand-china.com
 * @Date 2017/7/26 10:20
 * @name
 * @description
 */
@Table(name = "sys_resource")
public class SysResource extends BaseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resourceId;
    @Column
    private String url;
    @Column
    private String type;
    @Column
    private String name;
    @Column
    private String description;

    public Integer getResourceId() {
        return resourceId;
    }

    public SysResource setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SysResource setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getType() {
        return type;
    }

    public SysResource setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public SysResource setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SysResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
