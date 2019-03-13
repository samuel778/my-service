package com.micro.core.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jwt 配置
 * @author saml
 * @version 1.0
 * @date 2019-02
 */
@Configuration
public class JwtConfig {
    /**
     *
     */
    @Bean
    public JwtTokenUtils jwtTokenUtils() {
        JwtTokenUtils jwtTokenUtil = new JwtTokenUtils();
        jwtTokenUtil.setSecret("1");
        return jwtTokenUtil;
    }
}
