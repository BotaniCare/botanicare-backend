package com.backend.botanicare.repository;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.PlantPicture;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class PlantRepositoryRelationTest {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantPictureRepository plantPictureRepository;

//    @Test
//    void testPlantAndPictureRelation() {
//        Plant plant = new Plant();
//        plant.setName("Lily");
//        plant.setType("Flower");
//        Plant savedPlant = plantRepository.save(plant);
//
//        PlantPicture plantPicture = new PlantPicture();
//        plantPicture.setPlantPicture(new byte[]{1, 2, 3});
//        plantPictureRepository.save(plantPicture);
//
//        Optional<PlantPicture> foundPicture = plantPictureRepository.findByPlant_PlantId(savedPlant.getPlantId());
//        assertTrue(foundPicture.isPresent());
//    }
}
