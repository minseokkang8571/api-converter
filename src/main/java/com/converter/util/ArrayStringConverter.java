package com.converter.util;

import java.util.Arrays;
import java.util.List;

public class ArrayStringConverter {

    private static final String REGEX = ",";

    private ArrayStringConverter() {

    }

    public static List<String> convert(String arrayStringValue) {
        String onlyValue = arrayStringValue.substring(1, arrayStringValue.length() - 1);
        return Arrays.asList(onlyValue.split(REGEX));
    }
}
