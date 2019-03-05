package com.itstyle.seckill.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author 周瑞忠
 * @description java类作用描述
 * @date 2019/3/4 20:17
 */

@Entity
@Table(name = "user")
@Getter
@Setter
public @Data class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
}
