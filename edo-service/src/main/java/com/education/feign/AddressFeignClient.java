package com.education.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * реализация noFeignClient
 */

@Component
public class AddressFeignClient {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
