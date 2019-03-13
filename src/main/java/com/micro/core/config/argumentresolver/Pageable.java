package com.micro.core.config.argumentresolver;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "分页请求信息")
public class Pageable {
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private int pageNumber = 1;
    /**
     * 每页显示数目
     */
    @ApiModelProperty(value = "每页数量")
    private int pageSize = 10;
    /**
     * 排序， id desc / name asc ...
     */
    @ApiModelProperty(value = "排序字段")
    private String orderBY;

}
