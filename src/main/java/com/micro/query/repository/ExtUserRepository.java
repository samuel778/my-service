package com.micro.query.repository;

import com.micro.query.mapper.ExtUserCarAuthInfo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户管理扩展
 *
 * @author zhonglong
 * 2019/2/28 12:30
 */
@MapperScan
@Repository
public interface ExtUserRepository {

    /**
     * 获取用户绑定的车辆认证信息
     *
     * @param id 用户id
     * @return 用户绑定的车辆认证信息
     */
    List<ExtUserCarAuthInfo> getUserCarAuthList(@Param(value = "id") Integer id);


}
