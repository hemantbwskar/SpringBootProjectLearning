package com.springbootproject.learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public ThreadPoolExecutor taskExecutor() {
        return new ThreadPoolExecutor(
                10, // core pool size
                50, // max pool size
                60L, // keep-alive time
                java.util.concurrent.TimeUnit.SECONDS, // time unit for keep-alive
                new java.util.concurrent.LinkedBlockingQueue<>() // work queue
        );
    }

}
