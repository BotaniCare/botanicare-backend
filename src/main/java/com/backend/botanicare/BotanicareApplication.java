package com.backend.botanicare;

import com.backend.botanicare.service.CheckWateringDateService;
import com.backend.botanicare.service.WateringService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotanicareApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotanicareApplication.class, args);
        new CheckWateringDateService().start();
    }

}