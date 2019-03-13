package com.micro.domain.enums;

/**
 * 用户（车主）类型枚举
 *
 * @author zhonglong
 * 2019/3/6 20:31
 */
public enum UserType {

    /**
     * 业主
     */
    OWNER(1, "业主"),
    /**
     * 商户
     */
    MERCHANT(2, "商户"),
    /**
     * 员工
     */
    STAFF(3, "员工"),
    /**
     * 企业
     */
    ENTERPRISE(4, "企业"),
    ;

    private int userType;

    private String typeName;

    UserType(int type, String name) {
        this.userType = type;
        this.typeName = name;
    }

    public int getUserType() {
        return userType;
    }

    public String getTypeName() {
        return typeName;
    }
}
