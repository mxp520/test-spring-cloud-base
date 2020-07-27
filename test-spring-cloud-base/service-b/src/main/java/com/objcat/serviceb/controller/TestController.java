package com.objcat.serviceb.controller;


import com.objcat.serviceb.client.ServiceAFeignClint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Autowired
    private ServiceAFeignClint serviceAFeignClint;

    @GetMapping("print")
    public String print(){
        String result= serviceAFeignClint.print();
        return "b to a 访问结果："+result;
    }

    @Value("${name}")
    private String name;

    @RequestMapping("/hello")
    String hello() {
        return name;
    }
}
