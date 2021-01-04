package com.converter.controller;

import com.converter.dto.User;
import com.converter.service.MockyService;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "json", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getJson() {
        return mockyService.jsonToUrlencoded();
    }

    @GetMapping(value = "url", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUrlencoded() {
        return mockyService.urlencodedToJson();
    }
}
