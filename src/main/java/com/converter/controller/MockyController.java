package com.converter.controller;

import com.converter.dto.User;
import com.converter.error.ErrorResponse;
import com.converter.error.exception.ErrorCode;
import com.converter.service.MockyService;
import feign.codec.DecodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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

    @ExceptionHandler(DecodeException.class)
    protected ResponseEntity<ErrorResponse> decodeError(DecodeException e) {
        log.error("DecodeException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.FEIGN_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
