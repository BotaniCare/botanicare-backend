//package com.backend.botanicare.service;
//
//import com.backend.botanicare.model.Plant;
//import com.backend.botanicare.model.PlantPicture;
//import com.backend.botanicare.repository.PlantRepository;
//import com.backend.botanicare.repository.PlantPictureRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class PlantServiceTest {
//
//    @Mock
//    private PlantRepository plantRepository;
//
//    @Mock
//    private PlantPictureRepository plantPictureRepository;
//
//    @InjectMocks
//    private PlantService plantService;
//
//    private Plant plant;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        plant = new Plant();
//        plant.setPlantId(1);
//        plant.setName("Tulip");
//        plant.setType("Flower");
//    }
//
////    @Test
////    void testCreatePlant_withImage() {
////        byte[] imageData = "image-data".getBytes();
////        when(plantRepository.save(any(Plant.class))).thenAnswer(i -> i.getArguments()[0]);
////        PlantPicture plantPicture = new PlantPicture();
////        plantPicture.setPlantPicture(imageData);
////        plant.setPlantPicture(plantPicture);
////        Plant createdPlant = plantService.createPlant(plant);
////
////        assertNotNull(createdPlant);
////        assertNotNull(createdPlant.getPlantPicture());
////        assertArrayEquals(imageData, createdPlant.getPlantPicture().getPlantPicture());
////        assertEquals(createdPlant, createdPlant.getPlantPicture().getPlant());
////
////        verify(plantRepository, times(1)).save(any(Plant.class));
////    }
//
//    @Test
//    void testCreatePlant_withoutImage() {
//        when(plantRepository.save(any(Plant.class))).thenAnswer(i -> i.getArguments()[0]);
//        plant.setPlantPicture(null);
//        Plant createdPlant = plantService.createPlant(plant);
//
//        assertNotNull(createdPlant);
//        assertNull(createdPlant.getPlantPicture());
//
//        verify(plantRepository, times(1)).save(any(Plant.class));
//    }
//
//    @Test
//    void testCreatePlantWithSQLInjection() {
//        plant.setName("'; DROP TABLE Plants; --");
//        byte[] imageData = "image-data".getBytes();
//
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            plantService.createPlant(plant);
//        });
//
//        assertEquals("The plant name contains invalid characters", thrown.getMessage());
//    }
//
////    @Test
////    void testUpdatePlant() {
////        byte[] imageData = "updated-image-data".getBytes();
////        when(plantRepository.existsById(1)).thenReturn(true);
////        when(plantRepository.save(any(Plant.class))).thenAnswer(i -> i.getArguments()[0]);
////
////        Plant updatedPlant = plantService.updatePlant(1, plant);
////
////        assertNotNull(updatedPlant);
////        assertNotNull(updatedPlant.getPlantPicture());
////        assertArrayEquals(imageData, updatedPlant.getPlantPicture().getPlantPicture());
////
////        verify(plantRepository, times(1)).save(any(Plant.class));
////    }
//
//    @Test
//    void testDeletePlant() {
//        when(plantRepository.findById(1)).thenReturn(Optional.of(plant));
//
//        plantService.deletePlant(1);
//
//        verify(plantRepository, times(1)).deleteById(1);
//    }
//}
