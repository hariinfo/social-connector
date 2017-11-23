package com.stepan.twitter;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmitterConfiguration {

    @Bean
    public JsonParser jsonParser(){
        JsonParser parser = new JacksonJsonParser();
        return parser;
    }
}
