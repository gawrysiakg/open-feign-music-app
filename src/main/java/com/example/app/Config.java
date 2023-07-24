package com.example.app;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Decoder feignDecoder(){
        return new JacksonDecoder();
    }

    @Bean
    public OkHttpClient client() { //needed for feign patch mapping
        return new OkHttpClient();
    }
    // needed for feign patch mapping
    // https://stackoverflow.com/questions/61641977/spring-cloud-starter-openfeign-invalid-http-method-patch-executing-patch

}
