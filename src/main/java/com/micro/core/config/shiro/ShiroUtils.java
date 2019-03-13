package com.micro.core.config.shiro;

import com.micro.utils.lang3.UuidUtils;
import org.apache.shiro.crypto.hash.SimpleHash;


/**
 * shiro 加密工具，定义盐值加密方式。
 * mian 函数用于加密测试
 *
 * @author saml
 */
public class ShiroUtils {
    static String hashAlgorithmName = "md5";
    static int hashIterations = 2;

    public static String randomSalt() {
        return UuidUtils.getSalt();
    }

    public static String randomPasswd() {
        return UuidUtils.getPasswd();
    }

    public static String encrypt(String password, String salt) {
        return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
    }

}
