package com.converter.controller;

import com.converter.service.ProxyService;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
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
    public JSONObject proxy(@RequestBody String string) {
        return proxyService.urlencodedToJson(string);
    }

    @PostMapping(value = "/proxy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String proxy(@RequestBody JSONObject object) {
        return proxyService.jsonToUrlencoded(object);
    }
}