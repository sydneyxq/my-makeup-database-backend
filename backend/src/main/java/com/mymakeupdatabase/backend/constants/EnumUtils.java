package com.mymakeupdatabase.backend.constants;

import java.util.Arrays;

public class EnumUtils {
    public static <EnumType extends Enum<EnumType>> EnumType fromStringIgnoreCase(Class<EnumType> enumClass, String value) {

        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty");
        }

        String normalizedValue = value.toUpperCase();

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(enumConstant -> enumConstant.name().equalsIgnoreCase(normalizedValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid value: " + value));
    }
}
