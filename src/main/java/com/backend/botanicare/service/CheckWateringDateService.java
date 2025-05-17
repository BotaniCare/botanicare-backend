package com.backend.botanicare.service;

public class CheckWateringDateService extends Thread {

    WateringService wateringService;

    public void run() {
        while (true) {
            wateringService.hourlyWaterCheck();
            try {
                sleep(3600000); // sleep für 1h - check jede Stunde
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
