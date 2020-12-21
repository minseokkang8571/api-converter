package com.converter.service;

import com.converter.client.MockyClient;
import com.converter.util.BodyConverter;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MockyService {

    private final MockyClient mockyClient;

    public MockyService(MockyClient mockyClient) {
        this.mockyClient = mockyClient;
    }

    public String jsonToUrlencoded() {
        return BodyConverter.convertToUrlEncoded(mockyClient.getJson());
    }

    public JSONObject urlencodedToJson() {
        return BodyConverter.convertToJson(mockyClient.getUrlencoded());
    }
}
