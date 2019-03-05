package com.itstyle.seckill.common.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "high_ticket")
public @Data class HighTicket implements Serializable {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "rec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recId;

    /**
     * 票类型：SINGLE-高铁单程票 SUCCESSIVE_3DAY-高铁连续三日券 STRETCH_3DAY-高铁弹性三日券 STANDARD_5DAY-高铁标准5日券 SUPER_5DAY-高铁特级五日券
     */
    @Column(name = "ticket_type")
    private String ticketType;

    /**
     * 标题
     */
    @Column(name = "ticket_name")
    private String ticketName;

    /**
     * 预订须知
     */
    @Column(name = "book_notice")
    private String bookNotice;

    /**
     * 状态：WAIT_SALE-待上架 ON_SALE-已上架 NO_SALE-已下架
     */
    private String status;

    /**
     * 序号
     */
    @Column(name = "index_num")
    private Integer indexNum;

    /**
     * 是否删除：0-否 1-是
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    /**
     * 上架人ID
     */
    @Column(name = "up_user_id")
    private Integer upUserId;

    /**
     * 上架人
     */
    @Column(name = "up_user_name")
    private String upUserName;

    /**
     * 上架时间
     */
    @Column(name = "up_time")
    private Integer upTime;

    /**
     * 下架人ID
     */
    @Column(name = "down_user_id")
    private Integer downUserId;

    /**
     * 下架人
     */
    @Column(name = "down_user_name")
    private String downUserName;

    /**
     * 下架时间
     */
    @Column(name = "down_time")
    private Integer downTime;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private Integer creatorId;

    /**
     * 创建人编码
     */
    @Column(name = "creator_code")
    private String creatorCode;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建组织
     */
    @Column(name = "create_org_id")
    private Integer createOrgId;

    /**
     * 创建组织名称
     */
    @Column(name = "create_org_name")
    private String createOrgName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Integer createTime;

    /**
     * 更新人ID
     */
    @Column(name = "modifier_id")
    private Integer modifierId;

    /**
     * 更新人
     */
    private String modifier;

    /**
     * 更新时间
     */
    @Column(name = "modify_time")
    private Integer modifyTime;

    /**
     * 描述
     */
    @Column(name = "ticket_desc")
    private String ticketDesc;
}
