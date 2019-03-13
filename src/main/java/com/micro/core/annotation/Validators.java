/**
 * 
 */
package com.micro.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数校验
 * @author radioend
 * date 2014-3-21 上午11:00:14
 * Copyright: 版权所有(C) 2014
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Validators {
	String[] value(); 
}
