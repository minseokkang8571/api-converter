package com.converter.service;

import com.converter.dto.User;
import com.converter.util.BodyConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProxyService {

    public User convertToJson(@ModelAttribute User user) {
        return user;
    }

    public String jsonToUrlencoded(@RequestBody User user) {
        return BodyConverter.convertToUrlEncoded(user);
    }
}
