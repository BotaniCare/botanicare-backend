package com.backend.botanicare.service;

import com.backend.botanicare.exceptions.PlantNotFoundException;
import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.PlantPicture;
import com.backend.botanicare.repository.PlantRepository;
import com.backend.botanicare.repository.PlantPictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;
    private final PlantPictureRepository plantPictureRepository;

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Plant getPlantById(Integer plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);
        return plant.orElseThrow(() -> new PlantNotFoundException("No plant found with id " + plantId));
    }

    @Transactional
    public Plant createPlant(Plant plant, byte[] plantPictureData) {
        if (plantPictureData == null || plantPictureData.length == 0) {
            throw new IllegalArgumentException("The image cannot be empty");
        }

        String plantName = plant.getName();
        if (containsSQLInjection(plantName)) {
            throw new IllegalArgumentException("The plant name contains invalid characters");
        }

        Plant savedPlant = plantRepository.save(plant);

        PlantPicture plantPicture = new PlantPicture();
        plantPicture.setPlant(savedPlant);
        plantPicture.setPlantPicture(plantPictureData);
        plantPictureRepository.save(plantPicture);

        return savedPlant;
    }

    private boolean containsSQLInjection(String input) {
        String[] invalidChars = {"'", "--", ";", "/*", "*/", "xp_", "char", "union", "select", "drop", "insert"};
        for (String invalidChar : invalidChars) {
            if (input != null && input.toLowerCase().contains(invalidChar)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public Plant updatePlant(Integer plantId, Plant updatedPlant, byte[] plantPictureData) {
        if (!plantRepository.existsById(plantId)) {
            return null;
        }

        updatedPlant.setPlantId(plantId);

        Plant savedPlant = plantRepository.save(updatedPlant);

        if (plantPictureData != null && plantPictureData.length > 0) {
            Optional<PlantPicture> existingPicture = plantPictureRepository.findByPlant_PlantId(plantId);
            existingPicture.ifPresent(plantPictureRepository::delete);

            PlantPicture plantPicture = new PlantPicture();
            plantPicture.setPlant(savedPlant);
            plantPicture.setPlantPicture(plantPictureData);

            plantPictureRepository.save(plantPicture);
        }

        return savedPlant;
    }

    @Transactional
    public void deletePlant(Integer plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);
        if (plant.isPresent()) {
            Optional<PlantPicture> plantPicture = plantPictureRepository.findByPlant_PlantId(plantId);
            plantPicture.ifPresent(plantPictureRepository::delete);

            plantRepository.deleteById(plantId);
        } else {
            throw new PlantNotFoundException("No plant found with id " + plantId);
        }
    }
}
