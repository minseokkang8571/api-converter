package com.converter.service;

import com.converter.util.BodyConverter;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProxyService {

    public JSONObject urlencodedToJson(@RequestBody String string) {
        return BodyConverter.convertToJson(string);
    }

    public String jsonToUrlencoded(@RequestBody JSONObject object) {
        return BodyConverter.convertToUrlEncoded(object);
    }
}
