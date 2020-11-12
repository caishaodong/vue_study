package com.dong.shop.service.impl;

import com.dong.shop.domain.entity.OrderInfo;
import com.dong.shop.mapper.OrderInfoMapper;
import com.dong.shop.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

}
