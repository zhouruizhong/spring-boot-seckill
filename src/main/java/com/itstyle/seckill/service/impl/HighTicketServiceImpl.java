package com.itstyle.seckill.service.impl;

import com.itstyle.seckill.common.dynamicquery.DynamicQuery;
import com.itstyle.seckill.common.entity.HighSaleChannel;
import com.itstyle.seckill.common.entity.HighTicket;
import com.itstyle.seckill.service.IHighSaleChannelService;
import com.itstyle.seckill.service.IHighTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("highTicketService")
public class HighTicketServiceImpl implements IHighTicketService {

    @Autowired
    private DynamicQuery dynamicQuery;

    @Autowired
    private IHighSaleChannelService highSaleChannelService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(HighTicket highTicket) {

        highTicket.setCreateOrgId(9999);
        highTicket.setCreateOrgName("有关部门");

        highTicket.setCreator("习近平");
        highTicket.setCreatorId(1);
        highTicket.setCreatorCode("001");
        highTicket.setCreateTime((int)System.currentTimeMillis());

        highTicket.setIsDeleted(0);

        highTicket.setDownTime(0);
        highTicket.setDownUserId(0);
        highTicket.setDownUserName("");

        highTicket.setModifier("");
        highTicket.setModifierId(0);
        highTicket.setModifyTime(0);

        highTicket.setUpTime(0);
        highTicket.setUpUserId(0);
        highTicket.setUpUserName("");

        dynamicQuery.save(highTicket);
        System.out.println("执行保存高铁票信息");

        HighSaleChannel highSaleChannel = new HighSaleChannel();
        highSaleChannel.setChannel("PERSONAL");
        highSaleChannel.setTicketId(highTicket.getRecId());
        highSaleChannelService.add(highSaleChannel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer highTicketId) {
        dynamicQuery.delete(HighTicket.class, highTicketId);
    }
}
