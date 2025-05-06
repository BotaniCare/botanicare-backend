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
    public Plant getPlantById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: Plant ID must be valid.");
            throw new IllegalArgumentException("Plant ID must be valid.");
        }
        return plantService.getPlantById(id);
    }

    @PostMapping
    public Plant createPlant(@RequestParam("name") String name,
                             @RequestParam("art") String art,
                             @RequestParam("raum") String raum,
                             @RequestParam("wasserbedarf") String wasserbedarf,
                             @RequestParam("standort") String standort,
                             @RequestParam("image") MultipartFile image) throws IOException {

        if (isNullOrEmpty(name) || isNullOrEmpty(art) || isNullOrEmpty(raum) || isNullOrEmpty(wasserbedarf) || isNullOrEmpty(standort)) {
            System.out.println("Error: All fields must be filled.");
            throw new IllegalArgumentException("All fields must be filled.");
        }

        if (image.isEmpty()) {
            System.out.println("Error: Image is required.");
            throw new IllegalArgumentException("Image is required.");
        }

        Plant newPlant = new Plant();
        newPlant.setName(name);
        newPlant.setArt(art);
        newPlant.setRaum(raum);
        newPlant.setWasserbedarf(wasserbedarf);
        newPlant.setStandort(standort);
        newPlant.setImage(image.getBytes());

        return plantService.createPlant(newPlant);
    }

    @PutMapping("/{id}")
    public Plant updatePlant(@PathVariable Long id,
                             @RequestParam("name") String name,
                             @RequestParam("art") String art,
                             @RequestParam("raum") String raum,
                             @RequestParam("wasserbedarf") String wasserbedarf,
                             @RequestParam("standort") String standort,
                             @RequestParam("image") MultipartFile image) throws IOException {

        if (id == null || id <= 0) {
            System.out.println("Error: Plant ID must be valid.");
            throw new IllegalArgumentException("Plant ID must be valid.");
        }

        if (isNullOrEmpty(name) || isNullOrEmpty(art) || isNullOrEmpty(raum) || isNullOrEmpty(wasserbedarf) || isNullOrEmpty(standort)) {
            System.out.println("Error: All fields must be filled.");
            throw new IllegalArgumentException("All fields must be filled.");
        }

        Plant updatedPlant = new Plant();
        updatedPlant.setId(id);
        updatedPlant.setName(name);
        updatedPlant.setArt(art);
        updatedPlant.setRaum(raum);
        updatedPlant.setWasserbedarf(wasserbedarf);
        updatedPlant.setStandort(standort);
        updatedPlant.setImage(image.getBytes());

        return plantService.updatePlant(id, updatedPlant);
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable Long id) {
        if (id == null || id <= 0) {
            System.out.println("Error: Plant ID must be valid.");
            throw new IllegalArgumentException("Plant ID must be valid.");
        }
        plantService.deletePlant(id);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
