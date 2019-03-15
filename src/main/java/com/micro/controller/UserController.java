package com.micro.controller;

import com.micro.core.config.argumentresolver.Pageable;
import com.micro.core.config.mybatis.MyPageHelper;
import com.micro.core.config.swagger2.MyApiRealm;
import com.micro.query.JsonResult;
import com.micro.query.repository.ExtUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author saml
 * @version 1.0
 * @date 2019-03
 */
@Api(tags = "用户管理")
@MyApiRealm
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private ExtUserRepository extUserRepository;


    @ApiOperation(value = "用户分页")
    @RequestMapping("/pageUser")
    public JsonResult pageUser(Pageable pageable) {
        MyPageHelper.startPage(pageable);
//        extUserRepository.pageUser(pageUser);
        return JsonResult.successResult();
    }
}
