package com.converter.util;

import com.converter.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class BodyConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JSONParser jsonParser = new JSONParser();

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
                        e.printStackTrace();
                    }

                    return null;
                })
                .filter(value -> value != null)
                .collect(joining("&"));
    }

    public static JSONObject convertToJson(User user) {
        try {
            String userString = objectMapper.writeValueAsString(user);
            JSONObject userJson = (JSONObject) jsonParser.parse(userString);

            return userJson;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}