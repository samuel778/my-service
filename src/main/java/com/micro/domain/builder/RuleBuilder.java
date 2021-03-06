package com.micro.domain.builder;

import com.micro.domain.entry.Rule;

public class RuleBuilder {
    private Rule self;

    private RuleBuilder() {
        return;
    }

    public RuleBuilder(Rule self) {
        this.self = self;
    }

    /**
     * 建议不在本方法上进行修改,请复制再修改
     * @param name 字段名（name） 1、以英文单词命名，全部使用小写英文字母，多个英文单词以下划线间隔。 2、不要使用单词复数，也不要使用数字，除下划线外不要使用其他特殊字符。 3、尽量不要使用英文缩写，ip/url等常见的除外。 4、数据表中如无备注，建议表中的第一个id字段一定是主键且为自动增长。bigint类型，主键。 5、数据表中建议含有create_time/update_time字段，timestamp类型。 6、外键或引用外部数据表的字段时，命名方式为：外部数据表名+下划线+外部字段名，如：user_id/order_price。 7、可根据需要建立status,type,flag等类似的字段，tinyint类型，长度为1。
     * @param type 字段类型（type） 1、integer 整数型 2、decimal 小数型 3、string 字符串 4、text 文本 5、timestamp 时间戳
     * @param size 字段长度（size） 1、数字型字段1-11 2、字符串型字段1-1024
     * @param notNull 禁止空值 除text类型、timestamp类型字段外，其他类型字段禁止null值，需设置为not null。
     * @param keyDefault 字段默认值（default） 1、数字型字段默认为：0 2、字符型字段默认为：'' 3、时间型字段默认为：null def= 
     * @param comment 字段备注（comment） 除主键id外，其他字段建议添加中文备注。 def=
     * @param unsigned 禁止无符号数（unsigned） 整数型字段均为有符号数。 def=1
     * @param scale 小数点后位数（scale） 此为小数型字段所特有，小数型字段必须添加。
     * @param sort 字段排序 常用的字段排在前面，不常用的字段排在后面。
     * @param updateTime 更新时间
     * @param updateUserId 更新者 def=0
     * @param isValid 逻辑删除使用 1有效 0无效 def=b'1'
     * @param sourceData text,源数据，json串
    */
    public static Rule create(String name, String type, String size, String notNull, String keyDefault, String comment, Integer unsigned, java.math.BigDecimal scale, Integer sort, java.util.Date updateTime, Integer updateUserId, Boolean isValid, String sourceData) {
        Rule self = new Rule();
        self.setName(name);
        self.setType(type);
        self.setSize(size);
        self.setNotNull(notNull);
        self.setKeyDefault(keyDefault);
        self.setComment(comment);
        self.setUnsigned(unsigned);
        self.setScale(scale);
        self.setSort(sort);
        self.setUpdateTime(updateTime);
        self.setUpdateUserId(updateUserId);
        self.setIsValid(isValid);
        self.setSourceData(sourceData);
        return self;
    }

    /**
     * 建议不在本方法上进行修改,请复制再修改
     * @param name 字段名（name） 1、以英文单词命名，全部使用小写英文字母，多个英文单词以下划线间隔。 2、不要使用单词复数，也不要使用数字，除下划线外不要使用其他特殊字符。 3、尽量不要使用英文缩写，ip/url等常见的除外。 4、数据表中如无备注，建议表中的第一个id字段一定是主键且为自动增长。bigint类型，主键。 5、数据表中建议含有create_time/update_time字段，timestamp类型。 6、外键或引用外部数据表的字段时，命名方式为：外部数据表名+下划线+外部字段名，如：user_id/order_price。 7、可根据需要建立status,type,flag等类似的字段，tinyint类型，长度为1。
     * @param type 字段类型（type） 1、integer 整数型 2、decimal 小数型 3、string 字符串 4、text 文本 5、timestamp 时间戳
     * @param size 字段长度（size） 1、数字型字段1-11 2、字符串型字段1-1024
     * @param notNull 禁止空值 除text类型、timestamp类型字段外，其他类型字段禁止null值，需设置为not null。
     * @param keyDefault 字段默认值（default） 1、数字型字段默认为：0 2、字符型字段默认为：'' 3、时间型字段默认为：null def= 
     * @param comment 字段备注（comment） 除主键id外，其他字段建议添加中文备注。 def=
     * @param unsigned 禁止无符号数（unsigned） 整数型字段均为有符号数。 def=1
     * @param scale 小数点后位数（scale） 此为小数型字段所特有，小数型字段必须添加。
     * @param sort 字段排序 常用的字段排在前面，不常用的字段排在后面。
     * @param updateTime 更新时间
     * @param updateUserId 更新者 def=0
     * @param isValid 逻辑删除使用 1有效 0无效 def=b'1'
     * @param sourceData text,源数据，json串
    */
    public Rule update(String name, String type, String size, String notNull, String keyDefault, String comment, Integer unsigned, java.math.BigDecimal scale, Integer sort, java.util.Date updateTime, Integer updateUserId, Boolean isValid, String sourceData) {
        self.setName(name);
        self.setType(type);
        self.setSize(size);
        self.setNotNull(notNull);
        self.setKeyDefault(keyDefault);
        self.setComment(comment);
        self.setUnsigned(unsigned);
        self.setScale(scale);
        self.setSort(sort);
        self.setUpdateTime(updateTime);
        self.setUpdateUserId(updateUserId);
        self.setIsValid(isValid);
        self.setSourceData(sourceData);
        return self;
    }
}