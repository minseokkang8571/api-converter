package com.converter.util;

import java.util.Arrays;
import java.util.List;

public class ArrayStringConverter {

    private ArrayStringConverter() {

    }

    public static List<String> convert(String ArrayStringValue) {
        String onlyValue = ArrayStringValue.substring(1, ArrayStringValue.length() - 1);
        return Arrays.asList(onlyValue.split(","));
    }
}
