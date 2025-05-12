package com.backend.botanicare.controller;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.service.PlantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPlants() {
        try {
            return ResponseEntity.ok(plantService.getAllPlants());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting Plants");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlantById(@PathVariable("id") Integer plantId) {
        Plant plant = plantService.getPlantById(plantId);
        if (plant != null) {
            return ResponseEntity.ok(plant);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
    }

    @GetMapping("/{id}/picture")
    public ResponseEntity<Object> getPlantPicture(@PathVariable("id") Integer plantId) {
        try {
            Plant plant = plantService.getPlantById(plantId);
            if (plant == null || plant.getPlantPicture() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
            }

            byte[] imageBytes = plant.getPlantPicture().getPlantPicture();
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<?> createPlant(@RequestParam("name") String name,
                                         @RequestParam("type") String type,
                                         @RequestParam("waterNeed") String waterNeed,
                                         @RequestParam("sunlight") String sunlight,
                                         @RequestParam(value = "plantPicture", required = false) MultipartFile plantPicture) throws IOException {

        if (isNullOrEmpty(name) || isNullOrEmpty(type) || isNullOrEmpty(waterNeed) || isNullOrEmpty(sunlight)) {
            return ResponseEntity.badRequest().body("All fields must be completed.");
        }

        byte[] plantPictureData = null;
        if (plantPicture != null && !plantPicture.isEmpty()) {
            plantPictureData = plantPicture.getBytes();
        }

        Plant newPlant = new Plant();
        newPlant.setName(name);
        newPlant.setType(type);
        newPlant.setWaterNeed(waterNeed);
        newPlant.setSunlight(sunlight);

        try {
            System.out.println("Plant Data: " + newPlant.toString());
            System.out.println("PlantPicture Data: " + (plantPictureData != null ? plantPictureData.length : "No plant picture"));

            Plant createdPlant = plantService.createPlant(newPlant, plantPictureData);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlant);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Plant");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlant(@PathVariable("id") Integer plantId,
                                         @RequestParam("name") String name,
                                         @RequestParam("type") String type,
                                         @RequestParam("waterNeed") String waterNeed,
                                         @RequestParam("sunlight") String sunlight,
                                         @RequestParam(value = "plantPicture", required = false) MultipartFile plantPicture) throws IOException {

        if (isNullOrEmpty(name) || isNullOrEmpty(type) || isNullOrEmpty(waterNeed) || isNullOrEmpty(sunlight)) {
            return ResponseEntity.badRequest().body("All fields must be completed.");
        }

        byte[] plantPictureData = plantPicture != null ? plantPicture.getBytes() : null;
        Plant updatedPlant = new Plant();
        updatedPlant.setName(name);
        updatedPlant.setType(type);
        updatedPlant.setWaterNeed(waterNeed);
        updatedPlant.setSunlight(sunlight);

        try {
            Plant plant = plantService.updatePlant(plantId, updatedPlant, plantPictureData);
            if (plant != null) {
                return ResponseEntity.ok(plant);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating Plant");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlant(@PathVariable("id") Integer plantId) {
        try {
            plantService.deletePlant(plantId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
