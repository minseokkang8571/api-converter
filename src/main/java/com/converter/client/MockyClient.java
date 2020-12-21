package com.converter.client;

import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mocky", url = "${mocky-api.url}")
public interface MockyClient {

    @GetMapping("/0a1f12eeeb7f467890c626379e488adb")
    JSONObject getJson();

    @GetMapping("/4ee210e7262f4feba335d8da975fe393")
    String getUrlencoded();
}
