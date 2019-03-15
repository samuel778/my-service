package com.micro.query.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户车辆认证信息
 *
 * @author zhonglong
 * 2019/2/28 21:36
 */
@Data
@ApiModel("用户车辆认证信息")
public class ExtUserCarAuthInfo {

    @ApiModelProperty(value = "车牌号", required = true)
    private String plate;

    @ApiModelProperty(value = "创建时间（绑定时间）", required = true)
    private Date createTime;

    @ApiModelProperty(value = "认证id", required = true)
    private Integer authId;

    @ApiModelProperty(value = "认证状态", required = true)
    private Integer authStatus;

}
