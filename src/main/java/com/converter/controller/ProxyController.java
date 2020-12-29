package com.converter.controller;

import com.converter.dto.User;
import com.converter.service.ProxyService;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProxyController {

    private final ProxyService proxyService;

    public ProxyController(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @PostMapping(value = "/proxy", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public JSONObject proxyURL(@ModelAttribute User user) {
        return proxyService.convertToJson(user);
    }

    @PostMapping(value = "/proxy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String proxyJSON(@RequestBody User user) {
        return proxyService.jsonToUrlencoded(user);
    }
}