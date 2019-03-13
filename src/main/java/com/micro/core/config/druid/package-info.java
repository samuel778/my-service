/**
 * @link <a href='http://localhost:8804/druid/login.html'>访问</a>
 * @see <a href="https://my.oschina.net/liululee/blog/2223348">整合druid监控SQL执行</a>
 * @see <a href="https://github.com/alibaba/druid">官网</a>
 *
 原始配置
 #spring:
 #  datasource:
 #    driver-class-name: com.mysql.jdbc.Driver
 #    url: jdbc:mysql://172.27.0.3:3306/smart_parking?zeroDateTimeBehavior=convertToNull&useSSL=true
 #    username: haitunpark
 #    password: park9*pHT
 #    datasource:
 #      initialSize=0
 #      minIdle=5
 #      maxActive=40
 #      maxWait=1000
 #      dialect=com.mcro.framework.mybatis.dialect.MySQLDialect
 #      validationQuery=SELECT 1
 #      testWhileIdle=true
 #      testOnBorrow=false
 #      testOnReturn=false
 #      timeBetweenEvictionRunsMillis=60000
 #      minEvictableIdleTimeMillis=25200000
 #      removeAbandoned=true
 #      removeAbandonedTimeout=1800
 #      logAbandoned=true
 #      filters=stat,log4j
 #      connectionProperties=remarksReporting=true

 druid 配置：
 #spring:
 #  datasource:
 #    druid:
 #      url: jdbc:mysql://120.79.222.252:3306/javashop?allowMultiQueries:true&characterEncoding:UTF-8&zeroDateTimeBehavior:convertToNull
 #      username: root
 #      password: 123456
 #      driver-class-name: com.mysql.jdbc.Driver
 #      # 连接池配置
 #      initial-size: 1
 #      max-active: 20
 #      min-idle: 1
 #      max-wait: 10000
 #      pool-prepared-statements: true
 #      max-open-prepared-statements: 20
 #      validation-query: SELECT 1 FROM DUAL
 #      validation-query-timeout: 5000
 #      test-on-borrow: false
 #      test-on-return: false
 #      test-while-idle: true
 #      time-between-eviction-runs-millis: 60000
 #      min-evictable-idle-time-millis: 30000
 #      max-evictable-idle-time-millis: 60000
 #      removeAbandoned: true
 #      removeAbandonedTimeout: 1800
 #      connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
 #      max-pool-prepared-statement-per-connection-size: 20
 #      filters: stat,wall #filters: # 配置多个英文逗号分隔(统计，sql注入，log4j过滤)
 #    type: com.alibaba.druid.pool.DruidDataSource
 #app:
 #  druid:
 #    username: druid
 #    password: 123456
 #    reset-all: true

 */
package com.micro.core.config.druid;
