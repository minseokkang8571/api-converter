package com.converter.client;

import com.converter.config.FeignConfiguration;
import com.converter.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mocky", url = "${mocky-api.url}", configuration = {FeignConfiguration.class})
public interface MockyClient {

    @GetMapping("/0fb37d47d44949be99564fec38ea8f20")
    User getJson();

    @GetMapping("/966c12d47fc441f9a2d77e3e8725be14")
    MultiValueMap getUrlencoded();
}
