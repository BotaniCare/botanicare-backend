package com.backend.botanicare.controller;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.service.PlantService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @GetMapping("/{id}")
    public Plant getPlantById(@PathVariable("id") Integer plantId) {
        if (plantId == null || plantId <= 0) {
            System.out.println("Error: Plant ID must be valid.");
            throw new IllegalArgumentException("Plant ID must be valid.");
        }
        return plantService.getPlantById(plantId);
    }

    @PostMapping
    public Plant createPlant(@RequestParam("name") String name,
                             @RequestParam("type") String type,
                             @RequestParam("waterNeed") String waterNeed,
                             @RequestParam("sunlight") String sunlight,
                             @RequestParam("image") MultipartFile image) throws IOException {

        if (isNullOrEmpty(name) || isNullOrEmpty(type) || isNullOrEmpty(waterNeed) || isNullOrEmpty(sunlight)) {
            System.out.println("Error: All fields must be filled.");
            throw new IllegalArgumentException("All fields must be filled.");
        }

        if (image.isEmpty()) {
            System.out.println("Error: Image is required.");
            throw new IllegalArgumentException("Image is required.");
        }

        Plant newPlant = new Plant();
        newPlant.setName(name);
        newPlant.setType(type);
        newPlant.setWaterNeed(waterNeed);
        newPlant.setSunlight(sunlight);
        newPlant.setImage(image.getBytes());

        return plantService.createPlant(newPlant);
    }

    @PutMapping("/{id}")
    public Plant updatePlant(@PathVariable("id") Integer plantId,
                             @RequestParam("name") String name,
                             @RequestParam("type") String type,
                             @RequestParam("waterNeed") String waterNeed,
                             @RequestParam("sunlight") String sunlight,
                             @RequestParam("image") MultipartFile image) throws IOException {

        if (plantId == null || plantId <= 0) {
            System.out.println("Error: Plant ID must be valid.");
            throw new IllegalArgumentException("Plant ID must be valid.");
        }

        if (isNullOrEmpty(name) || isNullOrEmpty(type) || isNullOrEmpty(waterNeed) || isNullOrEmpty(sunlight)) {
            System.out.println("Error: All fields must be filled.");
            throw new IllegalArgumentException("All fields must be filled.");
        }

        Plant updatedPlant = new Plant();
        updatedPlant.setPlantId(plantId);
        updatedPlant.setName(name);
        updatedPlant.setType(type);
        updatedPlant.setWaterNeed(waterNeed);
        updatedPlant.setSunlight(sunlight);
        updatedPlant.setImage(image.getBytes());

        return plantService.updatePlant(plantId, updatedPlant);
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable("id") Integer plantId) {
        if (plantId == null || plantId <= 0) {
            System.out.println("Error: Plant ID must be valid.");
            throw new IllegalArgumentException("Plant ID must be valid.");
        }
        plantService.deletePlant(plantId);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
