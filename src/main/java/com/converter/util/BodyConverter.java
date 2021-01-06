package com.converter.util;

import com.converter.dto.User;
import com.converter.error.exception.ConvertException;
import com.converter.error.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public class BodyConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private BodyConverter() {

    }

    public static String convertToUrlEncoded(User user) {
        Map<String, String> map = objectMapper.convertValue(user, Map.class);
        return map.keySet().stream()
                .map(key -> {
                    try {
                        String value = String.valueOf(map.get(key));
                        return value != null && value.length() > 0
                                ? key + "=" + URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
                                : null;
                    } catch (UnsupportedEncodingException e) {
                        throw new ConvertException(e.getMessage(), e, ErrorCode.INVALID_INPUT_VALUE);
                    }
                })
                .filter(Objects::nonNull)
                .collect(joining("&"));
    }
}