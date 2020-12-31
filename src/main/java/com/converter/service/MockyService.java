package com.converter.service;

import com.converter.client.MockyClient;
import com.converter.dto.User;
import com.converter.util.BodyConverter;
import com.converter.util.UserDtoConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class MockyService {

    private final MockyClient mockyClient;

    public MockyService(MockyClient mockyClient) {
        this.mockyClient = mockyClient;
    }

    public String jsonToUrlencoded() {
        return BodyConverter.convertToUrlEncoded(mockyClient.getJson());
    }

    public User urlencodedToJson() {
        MultiValueMap<String, String> userMap = mockyClient.getUrlencoded();
        return UserDtoConverter.convert(userMap);
    }
}
