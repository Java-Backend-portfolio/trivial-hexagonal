package com.tecnocampus.trivial;

import com.tecnocampus.trivial.adapter.SpringAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrivialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppConfig.class, args);
    }

}

