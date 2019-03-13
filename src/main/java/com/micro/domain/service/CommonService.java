package com.micro.domain.service;

import com.micro.utils.captcha.ImageCode;

/**
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
public interface CommonService {
    /**
     * 创建图形验证码
     */
    ImageCode imageCodeCreate();

    /**
     * 验证码是否正确
     * @param imageToken    {@link ImageCode#imageToken}
     * @param userCodeValue 用户输入的验证码
     * @throws RuntimeException 验证错误直接抛异常
     */
    boolean imageCodeValidate(String imageToken, String userCodeValue) throws RuntimeException;
}
