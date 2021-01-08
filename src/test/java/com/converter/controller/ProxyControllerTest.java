package com.converter.controller;

import com.converter.dto.User;
import com.converter.service.ProxyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProxyController.class)
class ProxyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ProxyService proxyService;

    private static final String FORM_DATA = "id=1&firstName=kang&lastName=min&age=27&bookList=%5B%EC%95%84%EC%9D%B4%EC%8A%A4%ED%81%AC%EB%A6%BC%2C++%EC%8A%A4%ED%94%84%EB%A7%81%5D";
    private static final User USER_DTO = User.create(1, "kang", "min", 27, Arrays.asList("hi", "bye"));

    @Test
    void proxyJSON() throws Exception {
        // given
        final String content = objectMapper.writeValueAsString(USER_DTO);
        final String contentType = MediaType.APPLICATION_JSON_VALUE;

        // when
        final ResultActions actions = mockMvc.perform(post("/proxy")
                .content(content)
                .contentType(contentType));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void proxyURL() throws Exception {
        // given
        final String content = objectMapper.writeValueAsString(FORM_DATA);
        final String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;

        // when
        final ResultActions actions = mockMvc.perform(post("/proxy")
                .content(content)
                .contentType(contentType));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Header의 content-type은 form, Body는 JSON으로 요청이 들어올 경우 400에러가 발생")
    void headerFormBodyJsonMissMatchTest() throws Exception {
        // given
        final String content = objectMapper.writeValueAsString(USER_DTO);
        final String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;

        // when
        final ResultActions actions = mockMvc.perform(post("/proxy")
            .content(content)
            .contentType(contentType));

        // then
        actions
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("Header의 content-type은 JSON, Body는 form으로 요청이 들어올 경우 400에러가 발생")
    void headerJsonBodyFormMissMatchTest() throws Exception {
        // given
        final String content = FORM_DATA;
        final String contentType = MediaType.APPLICATION_JSON_VALUE;

        // when
        final ResultActions actions = mockMvc.perform(post("/proxy")
                .content(content)
                .contentType(contentType));

        // then
        actions
                .andExpect(status().is4xxClientError())
                .andExpect((result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(HttpMessageNotReadableException.class))))
                .andDo(print());
    }
}
// TODO:: 헤더와 바디가 다른경우 정의된 같은 예외를 controller에서 발생