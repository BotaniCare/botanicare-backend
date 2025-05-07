package com.backend.botanicare.service;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.repository.PlantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> getAllPlants() {
        try {
            return plantRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error in getAllPlants: " + e.getMessage());
            throw new RuntimeException("Error getting the Plants", e);
        }
    }

    public Plant getPlantById(Integer plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);
        return plant.orElse(null);
    }

    public Plant createPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public Plant updatePlant(Integer plantId, Plant updatedPlant) {
        if (!plantRepository.existsById(plantId)) {
            return null;
        }
        updatedPlant.setPlantId(plantId);
        return plantRepository.save(updatedPlant);
    }

    public void deletePlant(Integer plantId) {
        plantRepository.deleteById(plantId);
    }
}


