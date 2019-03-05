package com.itstyle.seckill.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * @author 周瑞忠
 * @description java类作用描述
 * @date 2019/3/4 20:18
 */
@Entity
@Table(name = "role")
public @Data class Role implements Serializable {

    @Column(name = "role_name")
    private String roleName;

    @Transient
    private User user;

    @Transient
    private List<Permission> permissions;
}
