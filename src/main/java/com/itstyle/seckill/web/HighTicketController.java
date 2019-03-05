package com.itstyle.seckill.web;

import com.itstyle.seckill.common.entity.HighTicket;
import com.itstyle.seckill.common.entity.Result;
import com.itstyle.seckill.service.IHighTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ="台湾高铁")
@RestController
@RequestMapping("highTicket")
public class HighTicketController {

    @Autowired
    private IHighTicketService highTicketService;

    @ApiOperation(value = "保存高铁票", nickname = "zrz")
    @PostMapping("/save")
    public Result save(HighTicket highTicket){
        highTicketService.add(highTicket);
        return Result.ok("保存成功！");
    }
}
