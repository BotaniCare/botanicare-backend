package com.backend.botanicare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class BotanicareApplication {

   //  static WateringService wateringService;

    public static void main(String[] args) {
        SpringApplication.run(BotanicareApplication.class, args);
      //  new CheckWateringDateService(wateringService).start();
    }

}