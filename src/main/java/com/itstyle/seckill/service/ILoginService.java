package com.itstyle.seckill.service;

import com.itstyle.seckill.common.entity.Role;
import com.itstyle.seckill.common.entity.User;

import java.util.Map;

/**
 * @author 周瑞忠
 * @description java类作用描述
 * @date 2019/3/4 20:07
 */
public interface ILoginService {

    User addUser(Map<String, Object> map);

    Role addRole(Map<String, Object> map);

    User findByName(String name);
}
