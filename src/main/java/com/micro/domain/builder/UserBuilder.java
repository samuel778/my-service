package com.micro.domain.builder;

import com.micro.domain.entry.User;

public class UserBuilder {
    private User self;

    private UserBuilder() {
        return;
    }

    public UserBuilder(User self) {
        this.self = self;
    }

    /**
     * 建议不在本方法上进行修改,请复制再修改
     * @param loginName 登录名称
     * @param password 登录密码
     * @param salt 盐值
     * @param realName 用户姓名
     * @param isSuperAdmin 是否超级管理员 1是 0 否 def=b'0'
     * @param mobile 手机号 def=
     * @param status 状态 1有效 0无效 def=1
     * @param createTime 创建时间
     * @param createUserId 创建者
     * @param updateTime 更新时间
     * @param updateUserId 更新者 def=0
     * @param isValid 逻辑删除使用 1有效 0无效 def=b'1'
    */
    public static User create(String loginName, String password, String salt, String realName, Boolean isSuperAdmin, String mobile, Integer status, java.util.Date createTime, Integer createUserId, java.util.Date updateTime, Integer updateUserId, Boolean isValid) {
        User self = new User();
        self.setLoginName(loginName);
        self.setPassword(password);
        self.setSalt(salt);
        self.setRealName(realName);
        self.setIsSuperAdmin(isSuperAdmin);
        self.setMobile(mobile);
        self.setStatus(status);
        self.setCreateTime(createTime);
        self.setCreateUserId(createUserId);
        self.setUpdateTime(updateTime);
        self.setUpdateUserId(updateUserId);
        self.setIsValid(isValid);
        return self;
    }

    /**
     * 建议不在本方法上进行修改,请复制再修改
     * @param loginName 登录名称
     * @param password 登录密码
     * @param salt 盐值
     * @param realName 用户姓名
     * @param isSuperAdmin 是否超级管理员 1是 0 否 def=b'0'
     * @param mobile 手机号 def=
     * @param status 状态 1有效 0无效 def=1
     * @param createTime 创建时间
     * @param createUserId 创建者
     * @param updateTime 更新时间
     * @param updateUserId 更新者 def=0
     * @param isValid 逻辑删除使用 1有效 0无效 def=b'1'
    */
    public User update(String loginName, String password, String salt, String realName, Boolean isSuperAdmin, String mobile, Integer status, java.util.Date createTime, Integer createUserId, java.util.Date updateTime, Integer updateUserId, Boolean isValid) {
        self.setLoginName(loginName);
        self.setPassword(password);
        self.setSalt(salt);
        self.setRealName(realName);
        self.setIsSuperAdmin(isSuperAdmin);
        self.setMobile(mobile);
        self.setStatus(status);
        self.setCreateTime(createTime);
        self.setCreateUserId(createUserId);
        self.setUpdateTime(updateTime);
        self.setUpdateUserId(updateUserId);
        self.setIsValid(isValid);
        return self;
    }
}