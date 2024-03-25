package com.example.service;

import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author djd
 * @since 2024-03-25
 */
public interface UserService extends IService<User> {
    public String getUser();

}
