package com.micro.domain.repository;

import com.micro.domain.entry.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@MapperScan
@Repository
public interface UserRepository extends Mapper<User>, MySqlMapper<User> {
}