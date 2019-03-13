package com.micro.domain.entry;

import lombok.Data;
import lombok.experimental.Accessors;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
/**
 * 1、数据表名（table）
 *  以英文单词命名，全部小写。多个英文单词以下划线间隔。
 *  不要使用单词复数，也不要使用数字，除下划线外不要使用其他特殊字符。
 *  尽量不要使用英文缩写，ip/url等常见的除外。
 *  所有表都要添加注释
 */
@Table(name = "rule")
public class Rule {
    /**
     * id,主键，自动递增
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    /**
     * 字段名（name）
1、以英文单词命名，全部使用小写英文字母，多个英文单词以下划线间隔。
2、不要使用单词复数，也不要使用数字，除下划线外不要使用其他特殊字符。
3、尽量不要使用英文缩写，ip/url等常见的除外。
4、数据表中如无备注，建议表中的第一个id字段一定是主键且为自动增长。bigint类型，主键。
5、数据表中建议含有create_time/update_time字段，timestamp类型。
6、外键或引用外部数据表的字段时，命名方式为：外部数据表名+下划线+外部字段名，如：user_id/order_price。
7、可根据需要建立status,type,flag等类似的字段，tinyint类型，长度为1。
     */
    private String name;

    /**
     * 字段类型（type）
1、integer 整数型
2、decimal 小数型
3、string 字符串
4、text 文本
5、timestamp 时间戳
     */
    private String type;

    /**
     * 字段长度（size）
1、数字型字段1-11
2、字符串型字段1-1024
     */
    private String size;

    /**
     * 禁止空值
除text类型、timestamp类型字段外，其他类型字段禁止null值，需设置为not null。
     */
    @Column(name = "not_null")
    private String notNull;

    /**
     * 字段默认值（default）
1、数字型字段默认为：0
2、字符型字段默认为：''
3、时间型字段默认为：null
     */
    @Column(name = "key_default")
    private String keyDefault;

    /**
     * 字段备注（comment）
除主键id外，其他字段建议添加中文备注。
     */
    private String comment;

    /**
     * 禁止无符号数（unsigned）
整数型字段均为有符号数。
     */
    private Integer unsigned;

    /**
     * 小数点后位数（scale）
此为小数型字段所特有，小数型字段必须添加。
     */
    private BigDecimal scale;

    /**
     * 字段排序
常用的字段排在前面，不常用的字段排在后面。
     */
    private Integer sort;

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

    /**
     * text,源数据，json串
     */
    @Column(name = "source_data")
    private String sourceData;
}