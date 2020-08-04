package com.quartz.servicequartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
//声明是注册中心客户端
@EnableEurekaClient
@EnableFeignClients
public class ServiceQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceQuartzApplication.class, args);
    }

}
