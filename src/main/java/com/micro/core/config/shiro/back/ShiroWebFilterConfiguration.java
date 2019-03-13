package com.micro.core.config.shiro.back;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 重写，重新定向login url
 * @see org.apache.shiro.spring.web.config.ShiroWebFilterConfiguration
 */
//@Configuration
public class ShiroWebFilterConfiguration extends AbstractShiroWebFilterConfiguration {

    @Bean
    @Override
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = super.shiroFilterFactoryBean();
        shiroFilterFactoryBean.setLoginUrl("/common/unAuth");
        return shiroFilterFactoryBean;
    }

}
