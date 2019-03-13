package com.micro.utils.lang3;

import java.math.BigDecimal;

public class NumberUtils {

    /**
     * 分转元
     * @param money
     * @return
     */
    public static BigDecimal yuan(Integer money){
        Double mDouble = Double.valueOf(money == null ? 0 : money);
        return BigDecimal.valueOf(mDouble/100);
    }

    /**
     * 数据库查询 Integer 到 BigDecimal 需要缩小100倍
     * @param money
     * @return
     */
    public static BigDecimal yuan(BigDecimal money){
        if (money == null){
            return BigDecimal.valueOf(0);
        }

        BigDecimal mult = BigDecimal.valueOf(100);
        return money.divide(mult);
    }

    public static Integer byObject(Object object){
        return Integer.valueOf(object == null ? "0" : object.toString());
    }

    public static Integer money(float money){
        return Integer.valueOf((int) (money*100));
    }


}
