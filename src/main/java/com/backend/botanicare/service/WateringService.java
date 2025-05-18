package com.backend.botanicare.service;

import com.backend.botanicare.exceptions.PlantNotExistException;
import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.WaterTracker;
import com.backend.botanicare.repository.PlantRepository;
import com.backend.botanicare.repository.WaterTrackerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class WateringService {

    // plan:
    // WaterNeed und Standort in waterInterval umrechnen
    // o mappe waterNeed (gering, normal, hoch) auf Tage
    // o mappe sunlight auf Punkte (sonnig, teilweise sonnig, hoch
    // o setze waterIntervalInHours, indem du die punkte für waterNeed mit den Punkten für sunlight multiplizierst
    // o nimm das aktuelle Datum und addiere waterInterval in hours da drauf
    // o speichere die plantId, und das waterDate  Eintrag in der DB
    // prüfe für alle Pflanzen täglich, ob currentDate >= waterDate ist
    // wenn ture, setze isWatered auf false und triggere push Benachrichtigung

    private final WaterTrackerRepository waterTrackerRepository;
    private final PlantRepository plantRepository;

    //ToDO: notification, dass beim bearbeiten einer Pflanze das Gießdatum komplett neu berechnet wird - bereits vergangene Tage ohne gießen werden nicht mit einberechnet

    public void startWaterTracking(Plant plant) {
        int plantId = plant.getId();
        String waterNeed = plant.getWaterNeed();
        String sunlight = plant.getSunlight();
        float waterNeedInHours;
        float waterIntervalInHours;
        float sunlightInPoints;

        switch (waterNeed) {
            case "gering":
                waterNeedInHours = 14 * 24; // 14 Tage * 24h
                break;
            case "normal":
                waterNeedInHours = 7 * 24;
                break;
            case "hoch":
                waterNeedInHours = 4 * 24;
                break;
            default:
                waterNeedInHours = 7 * 24;
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

        waterIntervalInHours = waterNeedInHours * sunlightInPoints;
        LocalDateTime.now().plusHours((long) waterIntervalInHours);

        WaterTracker waterTrackerForPlant = new WaterTracker(plantId, LocalDateTime.now().plusHours((long) waterIntervalInHours));
        waterTrackerRepository.save(waterTrackerForPlant);

        plant.setWaterDate(waterTrackerForPlant.getPlantWaterDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))); // nimm das waterDate der Pflanze, formatiere es und packe es in einern String
        plantRepository.save(plant); // speichere Pflanze mit dem waterDate ind er DB

    }
}
