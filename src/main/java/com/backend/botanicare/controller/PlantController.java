package com.backend.botanicare.controller;

import com.backend.botanicare.api.PlantsApi;
import com.backend.botanicare.mapper.PlantMapper;
import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.PlantDto;
import com.backend.botanicare.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class PlantController implements PlantsApi {

    private final PlantService plantService;

    @Override
    public ResponseEntity<List<PlantDto>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        List<PlantDto> plantDtos = PlantMapper.INSTANCE.toPlantDtoList(plants);
        return ResponseEntity.ok(plantDtos);
    }

    @Override
    public ResponseEntity<PlantDto> getPlantById(Integer plantId) {
        Plant plant = plantService.getPlantById(plantId);
        PlantDto plantDto = PlantMapper.INSTANCE.toPlantDto(plant);
        return ResponseEntity.ok(plantDto);
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

    @Override
    public ResponseEntity<PlantDto> addNewPlant(PlantDto plantDto) {
        // TODO: Should be createPlant WITHOUT any other params
        return PlantsApi.super.addNewPlant(plantDto);
    }

    @PostMapping
    // TODO: Swap to addNewPlant
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

    @Override
    public ResponseEntity<PlantDto> updatePlant(Integer id, PlantDto plantDto) {
        // TODO: Should be the old updatePlant Methode WITHOUT any other params
        return PlantsApi.super.updatePlant(id, plantDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlant(@PathVariable("id") Integer plantId,
                                         @RequestParam("name") String name,
                                         @RequestParam("type") String type,
                                         @RequestParam("waterNeed") String waterNeed,
                                         @RequestParam("sunlight") String sunlight,
                                         @RequestParam(value = "plantPicture", required = false) MultipartFile plantPicture) throws IOException {


        // TODO: Instead of custom method you can use Objects.isNonNull (or something like that) to check this case
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

    @Override
    public ResponseEntity<Void> deletePlantById(Integer id) {
        plantService.deletePlant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // TODO: Remove this
    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
