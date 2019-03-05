package com.itstyle.seckill.service.impl;

import com.itstyle.seckill.common.dynamicquery.DynamicQuery;
import com.itstyle.seckill.common.entity.HighSaleChannel;
import com.itstyle.seckill.service.IHighSaleChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("highSaleChannelService")
public class HighSaleChannelServiceImpl implements IHighSaleChannelService {

    @Autowired
    private DynamicQuery dynamicQuery;

    /**
     * Propagation.MANDATORY 表示当前方法必须在一个事务中运行，如果没有事务，将抛出异常
     *
     * Propagation.NESTED 表示如果当前方法正有一个事务在运行中，则该方法应该运行在一个嵌套事务中，
     *              被嵌套的事务可以独立于被封装的事务中进行提交或者回滚。
     *              如果封装事务存在，并且外层事务抛出异常回滚，那么内层事务必须回滚，
     *              反之，内层事务并不影响外层事务。
     *              如果封装事务不存在，则同propagation_required的一样
     *
     * Propagation.NEVER 表示当方法务不应该在一个事务中运行，如果存在一个事务，则抛出异常
     *
     * Propagation.NOT_SUPPORTED 表示该方法不应该在一个事务中运行。
     *              如果有一个事务正在运行，他将在运行期被挂起，直到这个事务提交或者回滚才恢复执行
     *
     * Propagation.REQUIRED 有事务就合并事务，没有事务就创建一个事务
     *
     * Propagation.REQUIRES_NEW 表示当前方法必须运行在它自己的事务中。一个新的事务将启动，
     *              而且如果有一个现有的事务在运行的话，则这个方法将在运行期被挂起，
     *              直到新的事务提交或者回滚才恢复执行。
     *
     * Propagation.SUPPORTS 表示当前方法不必需要具有一个事务上下文，但是如果有一个事务的话，它也可以在这个事务中运行
     * @param highSaleChannel
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
    public void add(HighSaleChannel highSaleChannel) {
        highSaleChannel.setIsDeleted(0);
        System.out.println("执行保存销售渠道");
        dynamicQuery.save(highSaleChannel);
    }
}
