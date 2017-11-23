package com.stepan;

import com.stepan.twitter.EmitterConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(EmitterConfiguration.class)
@SpringBootApplication
public class EmitterStarter {
    public static void main(String[] args) {
        SpringApplication.run(EmitterStarter.class, args);
    }
}
