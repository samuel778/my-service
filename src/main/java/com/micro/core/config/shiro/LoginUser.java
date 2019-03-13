package com.micro.core.config.shiro;

import com.micro.domain.entry.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 登录员工
 *
 * @author zhenghj
 * 2019/2/26 11:01
 */
@Data
@AllArgsConstructor
public class LoginUser {
    /**
     * 当前时间
     */
    private Date currentTime;

    /**
     * 员工ID
     */
    private Integer userId;

    /**
     * 用户信息
     */
    private User user;



}
