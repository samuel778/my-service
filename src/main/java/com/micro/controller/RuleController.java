package com.micro.controller;

import com.micro.query.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 控制器约束
 *
 * @author saml
 * @version 1.0
 * @date 2019-03
 */

/*
 * swagger注解，标识该控制器所表示的业务。
 */
@Api(tags = "控制器约定")
/*
 * URL请求映射。
 * 命名约定：
 * 1、与类名保持一致。如类名RuleController.class 则对应 @RequestMapping("/rule")
 * 2、驼峰命名。如CompanyUserController.class 则对应 @RequestMapping("/companyUser")
 * 3、首字母小写。
 */
@RequestMapping("/rule")
/*
 * 标记这是一个控制器。@RestController = @Controller + @ResponseBody
 * @ResponseBody ： 有了该方法，那么控制器类所有的方法无需加@ResponseBody注解
 */
@RestController
/**
 * 所有控制器将被前端调用，在后端没有调用记录。
 * 该标记取消idea对control类的未使warning
 */
@SuppressWarnings("unused")
/**
 * 控制器类
 * 命名约定：
 * 1、名词+Controller。通过名词可以看出该控制器类的业务职责
 * 2、首字母大小写。
 */
public class RuleController {
    /**
     * swagger注解，标志该方法所表示的具体功能
     */
    @ApiOperation("添加规则")
    /*
     * swagger注解，标志该方法所表示的具体功能
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "规则名称", required = true),
            @ApiImplicitParam(name = "memo", value = "描述")
    })
    /*
     * 与方法名保持一致
     */
    @RequestMapping(value = "/addRule")
    /**
     *  方法名
     *  操作(add,delete,update)+名词(如rule)，驼峰。
     *  查询(page,list,get)+名称(如rule)，驼峰。
     *
     *  参数名
     *  驼峰，首字母小写
     */
    JsonResult addRule(String name, String memo) {
        return JsonResult.successResult();
    }
}
