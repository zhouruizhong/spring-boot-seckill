package com.itstyle.seckill.service.impl;

import com.itstyle.seckill.common.dynamicquery.DynamicQuery;
import com.itstyle.seckill.common.entity.Permission;
import com.itstyle.seckill.common.entity.Role;
import com.itstyle.seckill.common.entity.User;
import com.itstyle.seckill.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 周瑞忠
 * @description java类作用描述
 * @date 2019/3/4 20:07
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private DynamicQuery dynamicQuery;

    @Override
    public User addUser(Map<String, Object> map) {
        User user = new User();
        user.setName(map.get("username").toString());
        user.setPassword((map.get("password").toString()));
        dynamicQuery.save(user);
        return user;
    }

    @Override
    public Role addRole(Map<String, Object> map) {
        //User user = userRepository.findOne(Long.valueOf(map.get("userId").toString()));
        String nativeSql = "";
        dynamicQuery.nativeQueryObject(nativeSql, new Object[]{});
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString());
        //role.setUser(user);
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        dynamicQuery.save(role);
        return role;
    }

    @Override
    public User findByName(String name) {
        return null;
    }
}
