package com.itstyle.seckill.common.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author 周瑞忠
 * @description java类作用描述
 * @date 2019/3/4 20:18
 */
@Entity
@Table(name = "permission")
public @Data class Permission implements Serializable {

    @Column(name = "permission")
    private String permission;
    @Transient
    private  Role role;
}
