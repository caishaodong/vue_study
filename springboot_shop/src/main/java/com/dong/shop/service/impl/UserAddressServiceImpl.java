package com.dong.shop.service.impl;

import com.dong.shop.domain.entity.UserAddress;
import com.dong.shop.mapper.UserAddressMapper;
import com.dong.shop.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户收货地址表 服务实现类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

}
