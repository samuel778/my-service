package com.micro.domain.entry;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "user")
public class User {
    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 登录名称
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 用户姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 是否超级管理员 1是 0 否
     */
    @Column(name = "is_super_admin")
    private Boolean isSuperAdmin;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态 1有效 0无效
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建者
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新者
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;

    /**
     * 逻辑删除使用 1有效 0无效
     */
    @Column(name = "is_valid")
    private Boolean isValid;
}