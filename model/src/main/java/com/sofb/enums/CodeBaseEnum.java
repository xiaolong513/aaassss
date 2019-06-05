package com.sofb.enums;

import java.util.Objects;

public interface CodeBaseEnum {
    int value();

    //按枚举的value获取枚举实例
    static <T extends CodeBaseEnum> T fromValue(Class<T> enumType, Integer value) {
        for (T object : enumType.getEnumConstants()) {
            if (Objects.equals(value, object.value())) {
                return object;
            }
        }
        throw new IllegalArgumentException("No enum value " + value + " of " + enumType.getCanonicalName());
    }
}
