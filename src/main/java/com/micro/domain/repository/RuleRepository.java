package com.micro.domain.repository;

import com.micro.domain.entry.Rule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * SQL使用规范
 *
 * 1、杜绝直接 SELECT *
 *  读取全部字段，只获取必要的字段，需要显示说明列属性
 *  即使需要所有字段，减少网络带宽消耗，能有效利用覆盖索引，表结构变更对程序基本无影响
 *  读取不需要的列会增加CPU、IO、NET消耗
 *  不能有效的利用覆盖索引
 *  使用SELECT *容易在增加或者删除字段后出现程序BUG
 *
 * 2、能确定返回结果只有一条时，使用 limit 1
 *
 *  在保证数据不会有误的前提下，能确定结果集数量时，多使用limit，尽快的返回结果。
 *
 * 3、禁止使用INSERT INTO t_xxx VALUES(xxx)，必须显示指定插入的列属性
 *
 * 4、禁止使用属性隐式转换
 *  SELECT uid FROM t_user WHERE phone=13812345678 会导致全表扫描，而不能命中phone索引。
 *
 *  phone是varchar类型，SQL语句带入的是整形，故不会命中索引，加个引号就好了：SELECT uid FROM t_user WHERE phone=’13812345678’
 *
 * 5、禁止在WHERE条件的属性上使用函数或者表达式
 *  SELECT uid FROM t_user WHERE from_unixtime(day)>='2017-02-15' 会导致全表扫描。
 *  正确的写法是：SELECT uid FROM t_user WHERE day>= unix_timestamp('2017-02-15 00:00:00')
 *
 * 6、使用join时，where条件尽量使用充分利用同一表上的索引
 *  如 select t1.a,t2.b * from t1,t2 and t1.a=t2.a and t1.b=123 and t2.c= 4 ，如果t1.c与t2.c字段相同，那么t1上的索引(b,c)就只用到b了。此时如果把where条件中的t2.c=4改成t1.c=4，那么可以用到完整的索引
 *  这种情况可能会在字段冗余设计（反范式）时出现
 *  正确选取inner join和left join
 *
 * 7、少用子查询，改用join
 *
 * 8、count计数
 *  首先count()、count(1)、count(col1)是有区别的，count()表示整个结果集有多少条记录，count(1)表示结果集里以primary key统计数量，绝大多数情况下count()与count(1)效果一样的，但count(col1)表示的是结果集里 col1 列 NOT null 的记录数。优先采用count()
 *  大数据量count是消耗资源的操作，甚至会拖慢整个库，查询性能问题无法解决的，应从产品设计上进行重构。例如当频繁需要count的查询，考虑使用汇总表
 *  遇到distinct的情况，group by方式可能效率更高。
 *
 * 9、减少与数据库交互的次数，尽量采用批量SQL语句
 *  INSERT ... ON DUPLICATE KEY UPDATE ...，插入行后会导致在一个UNIQUE索引或PRIMARY KEY中出现重复值，则执行旧行UPDATE，如果不重复则直接插入，影响1行。
 *  REPLACE INTO类似，但它是冲突时删除旧行。INSERT IGNORE相反，保留旧行，丢弃要插入的新行。
 *  INSERT INTO VALUES(),(),()，合并插入。
 *
 * 10、杜绝危险SQL
 *  去掉where 1=1 这样无意义或恒真的条件，如果遇到update/delete或遭到sql注入就恐怖了
 * SQL中不允许出现DDL语句。一般也不给予create/alter这类权限，但阿里云RDS只区分读写用户
 */
@MapperScan
@Repository
public interface RuleRepository extends Mapper<Rule>, MySqlMapper<Rule> {
}