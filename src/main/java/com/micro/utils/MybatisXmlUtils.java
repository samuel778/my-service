package com.micro.utils;

import com.micro.core.config.shiro.LoginUser;
import org.apache.shiro.SecurityUtils;

public class MybatisXmlUtils {
    /**
     * mybatis xlm 根据当前登录用户的company_id（=3假设）自动补充companyId字段
     * 使用方法:  ${@com.micro.utils.MybatisXmlUtils@CompanySegment("root.company_id")}
     * 结果将生成： root.company_id = 3
     * ==============================举例=====================================
     * select *
     * from park_card_order root
     * where root.is_valid = true
     * AND ${@com.micro.utils.MybatisXmlUtils@CompanySegment("root.company_id")}   =》 AND root.company_id = 3
     */
    public static String CompanySegment(String key) {
        LoginUser operateUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        return key + " = " + operateUser.getUser().getUpdateTime();
    }
}
