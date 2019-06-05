package com.sofb.common;


import org.springframework.util.CollectionUtils;

import java.util.List;

public class CollectionUtil extends CollectionUtils {

    public static <T> T getFirstChild(List<T> items){
        if(isEmpty(items))return null;
        return items.get(0);
    }

}
