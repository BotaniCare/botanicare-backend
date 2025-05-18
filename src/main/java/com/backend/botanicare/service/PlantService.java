package com.backend.botanicare.service;

import com.backend.botanicare.exceptions.PlantNotExistException;
import com.backend.botanicare.exceptions.PlantNotFoundException;
import com.backend.botanicare.exceptions.TaskNotFoundException;
import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.Task;
import com.backend.botanicare.repository.PlantRepository;
import com.backend.botanicare.repository.PlantPictureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public Plant createPlant(Plant plant) {

        String plantName = plant.getName();
        if (containsSQLInjection(plantName)) {
            throw new IllegalArgumentException("The plant name contains invalid characters");
        }

        if(plant.getPlantPicture() != null) {
            plant.getPlantPicture().setId(null);
        }

        return plantRepository.save(plant);
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
    public Plant updatePlant(Integer plantId, Plant updatedPlant) {
        if (!plantRepository.existsById(plantId)) {
            throw new PlantNotExistException("No plant found with id " + plantId);
        }

        updatedPlant.setId(plantId);

        return plantRepository.save(updatedPlant);
    }

    @Transactional
    public void deletePlant(Integer plantId) {
        Optional<Plant> plant = plantRepository.findById(plantId);
        if (plant.isPresent()) {
            plantRepository.deleteById(plantId);
        } else {
            throw new PlantNotFoundException("No plant found with id " + plantId);
        }
    }

    public List<Task> getTasksOfPlant(Integer plantId) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new PlantNotFoundException("No plant found with id " + plantId));
        return plant.getTasks();
    }

    public void addTaskForPlant(Integer plantId, Task task) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new PlantNotFoundException("No plant found with id " + plantId));
        plant.getTasks().add(task);
        plantRepository.save(plant);
    }

    public void deleteTaskOfPlant(Integer plantId, Integer taskId) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> new PlantNotFoundException("No plant found with id " + plantId));
        List<Task> tasks = new ArrayList<>(plant.getTasks());
        boolean wasRemoved = tasks.removeIf(task -> Objects.equals(task.getId(), taskId));
        if(!wasRemoved) {
            throw new TaskNotFoundException("No task found with id " + taskId);
        }
        plant.setTasks(tasks);
        plantRepository.save(plant);
    }

}
