package com.micro.utils.lang3;

import java.util.HashSet;
import java.util.Set;

/**
 * ID集合
 *
 * @author saml
 * @version 1.0
 * @date 2019-03
 */
public class IdCollection {
    Set<Integer> ids;

    public IdCollection() {
        ids = new HashSet<>();
    }

    public IdCollection add(Integer id) {
        ids.add(id);
        return this;
    }

    /**
     * 1,2,3,4,5
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer id : ids) {
            if (builder.length() == 0) {
                builder.append(id);
            } else {
                builder.append(",").append(id);
            }
        }
        return builder.toString();
    }

}
