//package com.backend.botanicare.repository;
//
//import com.backend.botanicare.model.Plant;
//import com.backend.botanicare.model.PlantPicture;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.util.Optional;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@Transactional
//class PlantPictureByteLobTest {
//
//    @Autowired
//    private PlantRepository plantRepository;
//
//    @Autowired
//    private PlantPictureRepository plantPictureRepository;
//
//    @Test
//    void testLobDataSaving() {
//        Plant plant = new Plant();
//        plant.setName("Tulip");
//        plant.setType("Flower");
//        Plant savedPlant = plantRepository.save(plant);
//
//        byte[] pictureData = new byte[]{100, 101, 102, 103, 104};
//        PlantPicture plantPicture = new PlantPicture();
//        plantPicture.setPlantPicture(pictureData);
//        plantPictureRepository.save(plantPicture);
//
//        Optional<PlantPicture> foundPicture = plantPictureRepository.findByPlant_PlantId(savedPlant.getPlantId());
//        assertTrue(foundPicture.isPresent());
//        assertArrayEquals(pictureData, foundPicture.get().getPlantPicture());
//    }
//}
