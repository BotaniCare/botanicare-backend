//package com.backend.botanicare.repository;
//
//import com.backend.botanicare.model.Plant;
//import com.backend.botanicare.model.PlantPicture;
//import com.backend.botanicare.model.Room;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class PlantRepositoryTest {
//
//    @Autowired
//    private PlantRepository plantRepository;
//
//    private Plant plant;
//
//    @BeforeEach
//    void setup() {
//        plant = new Plant();
//        plant.setName("Rose");
//        PlantPicture picture = new PlantPicture();
//        plant.setPlantPicture(picture);
//    }
//
//    @Test
//    void testSaveAndFindPlant() {
//        // Save the plant
//        Plant savedPlant = plantRepository.save(plant);
//
//        // Fetch it again by ID
//        Optional<Plant> found = plantRepository.findById(savedPlant.getPlantId());
//
//        assertTrue(found.isPresent());
//        assertEquals("Rose", found.get().getName());
//    }
//
//    @Test
//    void testDeletePlant() {
//        // Save the plant
//        Plant savedPlant = plantRepository.save(plant);
//
//        // Delete it
//        plantRepository.delete(savedPlant);
//
//        // Try to find it
//        Optional<Plant> found = plantRepository.findById(savedPlant.getPlantId());
//
//        assertFalse(found.isPresent());
//    }
//}
