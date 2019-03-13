package com.micro.controller;

import com.micro.core.config.swagger2.MyApiRealm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
@Api(tags = "当前登录用户")
@MyApiRealm
@RequestMapping("/realm")
@RestController
public class RealmController {
//    LoginUser operateUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
}
