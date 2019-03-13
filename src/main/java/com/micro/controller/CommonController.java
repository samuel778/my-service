package com.micro.controller;

import com.micro.core.config.jwt.JwtTokenUtils;
import com.micro.core.config.swagger2.MyApiCommon;
import com.micro.core.exception.LoginTimeoutException;
import com.micro.domain.entry.User;
import com.micro.domain.service.CommonService;
import com.micro.query.JsonResult;
import com.micro.query.mapper.LoginResponse;
import com.micro.utils.captcha.ImageCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
@Controller
@RequestMapping("/common")
@ResponseBody
@MyApiCommon
@Api(tags = "common")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @ApiOperation(value = "用户登录")
    @RequestMapping("/ajaxLogin")
    public JsonResult<LoginResponse> ajaxLogin(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        User user = new User();
        Map<String, String> map = new HashMap<>(16);
        //员工ID
        map.put("user_id", user.getId() + "");
        //token签发时间
        map.put("createTime", System.currentTimeMillis() + "");
        String sessionToken = jwtTokenUtils.getTokenByKey(map.toString());
        LoginResponse loginResponse = new LoginResponse(sessionToken, user.getLoginName(), user.getRealName(), user.getMobile());
        return JsonResult.successResult(loginResponse);
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     */
    @RequestMapping(value = "/unAuth")
    public Object unAuth() {
        throw new LoginTimeoutException();
    }

    @ApiOperation(value = "获取验证码")
    @RequestMapping("/imageCode")
    public JsonResult imageCode() {
        ImageCode imageCode = commonService.imageCodeCreate();
        return JsonResult.successResult(imageCode);
    }

}
