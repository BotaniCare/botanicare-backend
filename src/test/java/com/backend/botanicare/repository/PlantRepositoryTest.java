package com.backend.botanicare.repository;

import com.backend.botanicare.model.Plant;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PlantRepositoryTest {

    @Autowired
    private PlantRepository plantRepository;

    @Test
    void testSaveAndFindPlant() {
        Plant plant = new Plant();
        plant.setName("Rose");
        plant.setType("Flower");
        plant.setWaterNeed("Medium");
        plant.setSunlight("High");

        Plant savedPlant = plantRepository.save(plant);
        Optional<Plant> found = plantRepository.findById(savedPlant.getPlantId());

        assertTrue(found.isPresent());
        assertEquals("Rose", found.get().getName());
    }

    @Test
    void testDeletePlant() {
        Plant plant = new Plant();
        plant.setName("Tulip");
        Plant savedPlant = plantRepository.save(plant);
        plantRepository.delete(savedPlant);

        Optional<Plant> found = plantRepository.findById(savedPlant.getPlantId());
        assertFalse(found.isPresent());
    }
}
