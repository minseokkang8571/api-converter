package com.converter.client;

import com.converter.config.FeignConfiguration;
import com.converter.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mocky", url = "${mocky-api.url}", configuration = {FeignConfiguration.class})
public interface MockyClient {

    @GetMapping("/de82756b449c4bc8a594d089cbeb44bf")
    User getJson();

    @GetMapping("/c188d299a7564d758f52e172dc0c0028")
    MultiValueMap getUrlencoded();
}
