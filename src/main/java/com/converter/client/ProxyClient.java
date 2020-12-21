package com.converter.client;

import com.converter.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// 미사용.
@FeignClient(name = "feign", url = "http://localhost:8080", configuration = {FeignConfiguration.class})
public interface ProxyClient {

    @PostMapping(value = "/proxy", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String proxy(@RequestBody String string);

    @PostMapping(value = "/proxy", consumes = MediaType.APPLICATION_JSON_VALUE)
    Object proxy(@RequestBody Object object);
}
