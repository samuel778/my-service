package com.micro.core.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis扫描接口
 * 
 * @author liuzh
 * @since 2015-12-19 14:46
 */
@Configuration
/**
 * 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
 */
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(MapperScan.class);
        mapperScannerConfigurer.setBasePackage("com.micro.domain.repository,com.micro.query.repository");
        Properties properties = new Properties();
        properties.put("mappers", "tk.mybatis.mapper.common.Mapper,tk.mybatis.mapper.common.MySqlMapper");
		mapperScannerConfigurer.setProperties(properties );
        return mapperScannerConfigurer;
    }

}