package com.micro.utils;

import com.micro.core.exception.ServiceException;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class AssertUtils {
    public static void main(String[] args) {
    }

    @SafeVarargs
    public static <E> void exists(E param, String msg, E... val) {
        isTrue(val.length > 0, msg);
        notEmpty(param, msg);
        for (E v : val) {
            if (param.equals(v)) {
                return;
            }
        }
        throw new ServiceException(msg);
    }

    public static void notEmpty(Object param, String msg) {
        if (StringUtils.isEmpty(param)) {
            throw new ServiceException(msg);
        } else if (param instanceof Collection) {
            if (((Collection<?>) param).size() == 0) {
                throw new ServiceException(msg);
            }
        }
    }

    public static void isEmpty(Object param, String msg) {
        if (!StringUtils.isEmpty(param)) {
            throw new ServiceException(msg);
        } else if (param instanceof Collection) {
            if (((Collection<?>) param).size() > 0) {
                throw new ServiceException(msg);
            }
        }
    }

    public static void isTrue(boolean normal, String msg) {
        if (!normal) {
            throw new ServiceException(msg);
        }
    }

    public static void isFalse(boolean normal, String msg) {
        if (normal) {
            throw new ServiceException(msg);
        }
    }

    public static void between(int val, String msg, int start, int end) {
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }
        if (val > start || val < end) {
            throw new ServiceException(msg);
        }
    }
}
