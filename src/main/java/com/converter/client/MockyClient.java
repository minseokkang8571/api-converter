package com.converter.client;

import com.converter.config.FeignConfiguration;
import com.converter.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mocky", url = "${mocky-api.url}", configuration = {FeignConfiguration.class})
public interface MockyClient {

    @GetMapping("/9a13cd523bfe49929bbddc22502f2aa0")
    User getJson();

    @GetMapping("/4bf6be4ac7cb46a9bf101a6cd56dd9dd")
    MultiValueMap getUrlencoded();
}
