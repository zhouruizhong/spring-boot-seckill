package com.itstyle.seckill.common.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "high_sale_channel")
public @Data class HighSaleChannel {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "rec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recId;

    /**
     * 高铁票ID
     */
    @Column(name = "ticket_id")
    private Integer ticketId;

    /**
     * 产品投放渠道: PERSONAL-个人用户渠道 COMPANY-企业用户 渠道
     */
    private String channel;

    /**
     * 是否删除：0-否 1-是
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

}
