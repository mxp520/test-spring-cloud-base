package com.quartz.servicequartz.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("SERVICE-OBJCAT-A")
public interface ServiceAFeignClint {

    @GetMapping("print")
    public String print();
}
