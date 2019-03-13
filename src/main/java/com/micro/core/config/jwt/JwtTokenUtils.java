package com.micro.core.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.micro.utils.lang3.StringUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author saml
 */
@Getter
@Setter
public class JwtTokenUtils implements InitializingBean {
    /**
     * 私钥,
     * 若未配置，则自动生成
     * @see JwtTokenUtils#afterPropertiesSet
     */
    private String secret;
    /**
     * 默认12小时失效
     */
    private final long expTime = 12 * 60 * 60 * 1000;

    /**
     * jwt加密，有效时间12小时
     *
     * @param key 加密串
     */
    public String getTokenByKey(String key) {
        return getTokenByKey(key, expTime);
    }

    /**
     * jwt加密，自定义有效时间
     *
     * @param key 加密串
     * @param exp 超时时间，单位毫秒，12小时 = 12 * 60 * 60 * 1000
     */
    public String getTokenByKey(String key, long exp) {
        Date issueD = new Date();
        Date expD = new Date(issueD.getTime() + exp);
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create().withJWTId(key).withIssuedAt(issueD).withExpiresAt(expD).sign(algorithm);
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return token;
    }

    /**
     * 还原加密串
     */
    public String getKeyByToken(String token) throws SignatureVerificationException, TokenExpiredException,
            IllegalArgumentException {
        String key;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // Reusable verifier instance
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        key = jwt.getId();
        return key;
    }

    /**
     * 如果未配置secret，则系统随机生成。
     * 每次服务重启，刷新。
     */
    @Override
    public void afterPropertiesSet() {
        if (StringUtils.isEmpty(secret)) {
            secret = UUID.randomUUID().toString();
        }
    }

    /**
     * 分割字符串进SET
     */
    @SuppressWarnings("unchecked")
    public  Set<String> split(String str) {

        Set<String> set = new HashSet<>();
        if (org.springframework.util.StringUtils.isEmpty(str)){
            return set;
        }
        set.addAll(CollectionUtils.arrayToList(str.split(",")));
        return set;
    }

}
