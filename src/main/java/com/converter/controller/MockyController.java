package com.converter.controller;

import com.converter.service.MockyService;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mocky")
public class MockyController {

    private final MockyService mockyService;

    public MockyController(MockyService mockyService) {
        this.mockyService = mockyService;
    }

    @GetMapping("json")
    public String getJson() {
        return mockyService.jsonToUrlencoded();
    }

    @GetMapping("url")
    public JSONObject getUrlencoded() {
        return mockyService.urlencodedToJson();
    }
}
