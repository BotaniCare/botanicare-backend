package com.backend.botanicare.service;

import com.backend.botanicare.model.Plant;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PlantService {

    private final Map<Long, Plant> plantStorage = new HashMap<>();
    private long currentId = 1;

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plantStorage.values());
    }

    public Plant getPlantById(Long id) {
        return plantStorage.get(id);
    }

    public Plant createPlant(Plant plant) {
        plant.setId(currentId++);
        plantStorage.put(plant.getId(), plant);
        return plant;
    }

    public Plant updatePlant(Long id, Plant updatedPlant) {
        if (!plantStorage.containsKey(id)) {
            return null;
        }
        updatedPlant.setId(id);
        plantStorage.put(id, updatedPlant);
        return updatedPlant;
    }

    public void deletePlant(Long id) {
        plantStorage.remove(id);
    }
}

