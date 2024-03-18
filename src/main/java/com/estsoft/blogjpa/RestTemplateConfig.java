package com.estsoft.blogjpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration // 설정 파일
public class RestTemplateConfig { // RestTemplate 설정


    @Bean // RestTemplate Bean 등록
    public RestTemplate restTemplate() { // RestTemplate 생성
        return new RestTemplate();
    }
}
