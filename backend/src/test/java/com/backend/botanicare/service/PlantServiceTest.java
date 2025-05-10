package com.backend.botanicare.service;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.repository.PlantRepository;
import com.backend.botanicare.repository.PlantPictureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private PlantPictureRepository plantPictureRepository;

    @InjectMocks
    private PlantService plantService;

    private Plant plant;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        plant = new Plant();
        plant.setPlantId(1);
        plant.setName("Tulip");
        plant.setType("Flower");
    }

    @Test
    void testCreatePlant() {
        byte[] imageData = "image-data".getBytes();
        when(plantRepository.save(any(Plant.class))).thenReturn(plant);

        Plant createdPlant = plantService.createPlant(plant, imageData);

        assertNotNull(createdPlant);
        verify(plantRepository, times(1)).save(plant);
    }

    @Test
    void testCreatePlantWithSQLInjection() {

        String maliciousName = "'; DROP TABLE Plants; --";
        plant.setName(maliciousName);
        byte[] imageData = "image-data".getBytes();

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            plantService.createPlant(plant, imageData);
        });

        // assertEquals("Invalid input detected in plant name", thrown.getMessage());

        System.out.println("Testing with malicious input: " + maliciousName);
    }

    @Test
    void testUpdatePlant() {
        byte[] imageData = "updated-image-data".getBytes();
        when(plantRepository.existsById(1)).thenReturn(true);
        when(plantRepository.save(any(Plant.class))).thenReturn(plant);

        Plant updatedPlant = plantService.updatePlant(1, plant, imageData);

        assertNotNull(updatedPlant);
        verify(plantRepository, times(1)).save(any(Plant.class));
    }

    @Test
    void testDeletePlant() {
        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));

        plantService.deletePlant(1);

        verify(plantRepository, times(1)).deleteById(1);
    }
}
