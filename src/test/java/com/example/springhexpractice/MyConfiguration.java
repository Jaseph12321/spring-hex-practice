package com.example.springhexpractice;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public MyService getService() {
        return new MyService();
    }

}
