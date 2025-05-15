package com.backend.botanicare.service;

import com.backend.botanicare.model.Plant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WateringService {
    // WaterNeed und Standort in waterInterval umrechnen

        // mappe waterNeed (gering, normal, hoch) auf Tage
        // mappe sunlight auf Punkte (sonnig, teilweise sonnig, hoch
        // mappe Punkte auf Tage
        // multipliziere die punkte für waterNeed mit den Punkten für sunlight
        // setze waterInterval auf die Anzahla n Tagen, die der Punktzahl entspricht

    // waterDate mit aktuellem Datum + Gießintervall berechnen

    public void startWaterTracking (Plant plant){
        int plantId = plant.getId();
        String waterNeed = plant.getWaterNeed();
        String sunlight = plant.getSunlight();
        float waterNeedInHours;
        float waterIntervalInHours;
        float sunlightInPoints;

        switch (waterNeed) {
            case "gering":
                waterNeedInHours = 14*24; // 14 Tage * 24h
                break;
            case "normal":
                waterNeedInHours = 7*24;
                break;
            case "hoch":
                waterNeedInHours = 4*24;
                break;
            default:
                waterNeedInHours = 7*24;
                break;
        }

        switch (sunlight) {
            case "sonnig":
                sunlightInPoints = 0.75f;
                break;
            case "teilweise sonnig":
                sunlightInPoints = 1;
                break;
            case "nicht sonnig":
                sunlightInPoints = 1.25f;
                break;
            default:
                sunlightInPoints = 0;
                break;
        }

        waterIntervalInHours = waterNeedInHours*sunlightInPoints;





    }
}
