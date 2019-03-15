package com.micro.query.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户登录成功返回的信息
 *
 * @author zhonglong
 * 2019/2/22 17:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("用户登录")
public class LoginResponse {
    @ApiModelProperty("后续接口在head添加session_token=token")
    private Serializable token;

    @ApiModelProperty("登录名称")
    private String loginName;

    @ApiModelProperty("真实姓名")
    private String realName;


    @ApiModelProperty("手机号")
    private String mobile;
}
