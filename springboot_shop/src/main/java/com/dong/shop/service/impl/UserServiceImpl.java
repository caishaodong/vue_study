package com.dong.shop.service.impl;

import com.dong.shop.domain.entity.User;
import com.dong.shop.mapper.UserMapper;
import com.dong.shop.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
