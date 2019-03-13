package com.micro.utils.captcha;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.micro.core.config.jwt.JwtTokenUtils;
import com.micro.core.exception.ServiceException;
import com.micro.core.exception.SystemException;
import com.micro.utils.lang3.StringUtils;
import jodd.util.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * spring
 *
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
public class ImageCodeUtils {
    /**
     * 5分钟内有效
     */
    private final static long expTime = 5 * 60 * 1000;
    /**
     * 区分大小写,默认不区分大小写
     */
    private final static boolean caseSensitive = false;


    /**
     * 创建验证码，指定有效时间
     *
     * @param exp 超时时间，单位毫秒，12小时 = 12 * 60 * 60 * 1000
     */
    public static ImageCode create(long exp, JwtTokenUtils tokenUtil) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        String code = RandomStringUtils.random(4, VerifyCodeUtils.VERIFY_CODES);
        try {
            VerifyCodeUtils.outputImage(220, 80, os, code);
        } catch (IOException e) {
            throw new RuntimeException("验证码生成失败！");
        }
        ImageCode imageCode = new ImageCode();
        imageCode.setCode(code);
        imageCode.setImageBase64("data:image/png;base64," + Base64.encodeToString(os.toByteArray()));
        //code 进行加密
        imageCode.setImageToken(tokenUtil.getTokenByKey(code, exp));
        return imageCode;
    }

    /**
     * 创建验证码，默认5分钟有效
     */
    public static ImageCode create(JwtTokenUtils tokenUtil) {
        return create(ImageCodeUtils.expTime, tokenUtil);
    }

    /**
     * 验证：1，验证码不能超时；2，验证码是否准确
     * 默认不区分大小写
     *
     * @param imageToken    验证码token
     * @param userCodeValue 用户输入验证码值
     */
    public static boolean validate(String imageToken, String userCodeValue, JwtTokenUtils tokenUtil) {
        return validate(imageToken, userCodeValue, ImageCodeUtils.caseSensitive, tokenUtil);
    }

    /**
     * 验证：1，验证码不能超时；2，验证码是否准确
     * 默认不区分大小写
     *
     * @param imageToken    验证码token
     * @param userCodeValue 用户输入验证码值
     * @param caseSensitive 区分大小写，设置该值为true
     */
    public static boolean validate(String imageToken, String userCodeValue, boolean caseSensitive, JwtTokenUtils tokenUtil) {
        if (StringUtils.isEmpty(userCodeValue)) {
            throw new ServiceException("请输入验证码");
        }
        if (!StringUtils.isEmpty(imageToken)) {
            throw new ServiceException("imageToken不能为空");
        }
        String keyStr;
        try {
            keyStr = tokenUtil.getKeyByToken(imageToken);
        } catch (TokenExpiredException e) {
            throw new ServiceException("验证码超时");
        } catch (IllegalArgumentException | SignatureVerificationException e) {
            throw new ServiceException("验证码无效");
        }
        if (StringUtils.isEmpty(keyStr)) {
            throw new SystemException("keyStr 异常");
        }
        //区分大小写
        boolean eq = false;
        if (caseSensitive) {
            eq = userCodeValue.equals(keyStr);
        } else {
            eq = userCodeValue.toUpperCase().equals(keyStr.toUpperCase());
        }
        if (eq) {
            return true;
        } else {
            throw new ServiceException("验证码错误");
        }
    }

}
