package com.micro.core.config.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class PageableArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Pageable.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest req = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String orderBY = req.getParameter("orderBY");
        String pageNumber = req.getParameter("pageNumber");
        String pageSize = req.getParameter("pageSize");
        Pageable pageable = new Pageable();
        if (!StringUtils.isEmpty(orderBY)) {
            pageable.setOrderBY(orderBY);
        }
        if (!StringUtils.isEmpty(pageNumber)) {
            pageable.setPageNumber(Integer.parseInt(pageNumber));
        }
        if (!StringUtils.isEmpty(pageSize)) {
            pageable.setPageSize(Integer.parseInt(pageSize));
        }
        return pageable;

    }
}