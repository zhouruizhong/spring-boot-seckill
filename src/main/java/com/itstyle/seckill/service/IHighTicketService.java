package com.itstyle.seckill.service;

import com.itstyle.seckill.common.entity.HighTicket;

public interface IHighTicketService {

    void add(HighTicket highTicket);

    void delete(Integer highTicketId);
}
