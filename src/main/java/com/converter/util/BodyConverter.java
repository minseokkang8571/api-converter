package com.converter.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class BodyConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JSONParser jsonParser = new JSONParser();

    public static String convertToUrlEncoded(JSONObject obj) {
        Map<String, String> map = objectMapper.convertValue(obj, Map.class);
        return map.keySet().stream()
                .map(key -> {
                    try {
                        String value = String.valueOf(map.get(key));
                        if (value.charAt(0) == '{') {
                            String[] valueSplit = value.split(",");
                            for(String s: valueSplit) {
                                String replacedStr = s.replaceFirst("=", ":");
                                value = value.replace(s, replacedStr);
                            }
                        }
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

    public static JSONObject convertToJson(String paramIn) {
        try {
            String jsonStr = paramIn;
            String[] split = (paramIn.split("[=&]"));
            for (int i = 0; i < split.length; i++) {
                String decodedStr = URLDecoder.decode(split[i], StandardCharsets.UTF_8.toString());
                if(split[i].charAt(0) == '%') {
                    String[] decodedSplit = decodedStr.substring(1).split("[:,}]");
                    for(String s: decodedSplit) {
                        decodedStr = decodedStr.replace(s , "\"" + s.replaceAll("\\s", "") + "\"");
                    }
                    jsonStr = jsonStr.replace(split[i], decodedStr);
                } else {
                    jsonStr = jsonStr.replaceAll("(?<!\")" + split[i] + "(?!\")", "\"" + decodedStr + "\"");
                }
            }

            jsonStr = jsonStr
                    .replaceAll("(?<=\")" + "=" + "(?=[\"{])", ":")
                    .replaceAll("&", ",");
            jsonStr = "{" + jsonStr + "}";
            Object obj = jsonParser.parse(jsonStr);
            JSONObject jsonObj = (JSONObject) obj;

            return jsonObj;
        } catch (ParseException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}