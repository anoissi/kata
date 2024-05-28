package com.mowitnow.kata.infrastructure;

import com.mowitnow.kata.domain.service.MowerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MowItNowConfiguration {

    @Bean
    MowerService mowerService() {
        return new MowerService();
    }
}
