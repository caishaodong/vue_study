package com.dong.shop.global.config.aop.permission;


import com.dong.shop.global.enums.RoleEnum;

import java.lang.annotation.*;

/**
 * @Author caishaodong
 * @Date 2020-09-18 14:19
 * @Description
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    RoleEnum[] role();
}
