package com.backend.botanicare.service;
import com.backend.botanicare.repository.PlantRepository;
import com.backend.botanicare.repository.WaterTrackerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;

public class checkWateringDateService {

    WaterTrackerRepository waterTrackerRepository;
    PlantRepository plantRepository;

    @Scheduled(cron="0 0 * * * *")
    void hourlyWaterCheck (){
        waterTrackerRepository.findAll().forEach(waterTracker -> { // führe die folgenden Statements für alle Positionen im waterTracker aus
            int plantId = waterTracker.getPlantId(); // schau anhand des keys plantId in eine Position des waterTrackers
            if (!waterTracker.getPlantWaterDate().isAfter(LocalDateTime.now())){ // schau dir das für den key gespeicherte waterDate an, vergleiche es mit dem jetzigen Datum und überprüfe, ob es nicht in der zukunft liegt = heute oder vergangenheit
                if (!plantRepository.existsById(plantId)) { // wenn die Pflanze mit dieser Id nicht in der DB existiert...
                    waterTrackerRepository.deleteById(plantId); // ... lösche diesen waterTracker
                }
                plantRepository.findById(plantId).ifPresent(plant -> { // wenn das aktuelle Datum nicht ind er zukunft liegt, finde die zugehörige Pflanze ind er DB
                    plant.setIsWatered(false); // setze isWatered der Pflanze auf false
                    plantRepository.save(plant);  // speichere die Pflanze in der DB
                });
            }
        });
    }
    }

