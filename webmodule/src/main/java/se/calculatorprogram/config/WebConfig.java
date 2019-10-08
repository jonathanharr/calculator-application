package se.calculatorprogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * WebConfig, holds our RestTemplate and Atomic Long.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    private AtomicLong atomicLong = new AtomicLong();

    @Bean
    public AtomicLong atomicLong() {
        return atomicLong;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}