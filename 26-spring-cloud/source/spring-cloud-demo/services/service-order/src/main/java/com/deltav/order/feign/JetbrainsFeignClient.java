package com.deltav.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "jetbrains-guide", url = "https://www.jetbrains.com")
public interface JetbrainsFeignClient {
    @GetMapping("/help/idea/2024.2/language-and-region.html")
    String getHelp();
}
