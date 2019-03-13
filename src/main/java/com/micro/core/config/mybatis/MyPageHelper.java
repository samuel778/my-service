package com.micro.core.config.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.micro.core.config.argumentresolver.Pageable;

/**
 * @author saml
 */
public class MyPageHelper {
    public static <E> Page<E> startPage(Pageable pageable) {
        return PageHelper.startPage(pageable.getPageNumber(),pageable.getPageSize(),pageable.getOrderBY());
    }
}
