package com.dong.shop.service.impl;

import com.dong.shop.domain.entity.UserInfo;
import com.dong.shop.mapper.UserInfoMapper;
import com.dong.shop.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author caishaodong
 * @since 2020-11-12
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
