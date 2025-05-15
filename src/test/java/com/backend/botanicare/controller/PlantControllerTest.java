//package com.backend.botanicare.controller;
//
//import com.backend.botanicare.model.Plant;
//import com.backend.botanicare.model.PlantDto;
//import com.backend.botanicare.model.PlantPicture;
//import com.backend.botanicare.model.PlantPictureDto;
//import com.backend.botanicare.service.PlantService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//class PlantControllerTest {
//
//    @Mock
//    private PlantService plantService;
//
//    @InjectMocks
//    private PlantController plantController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
////    @Test
////    void testCreatePlant_Success() throws IOException {
////        // Arrange
////        MultipartFile mockPicture = new MockMultipartFile("plantPicture", "image.jpg", "image/jpeg", new byte[]{1, 2, 3});
////
////        Plant mockPlant = new Plant();
////        mockPlant.setPlantId(1);
////        mockPlant.setName("Rose");
////        mockPlant.setType("Flower");
////        mockPlant.setWaterNeed("Medium");
////        mockPlant.setSunlight("High");
////
////        when(plantService.createPlant(any(Plant.class))).thenReturn(mockPlant);
////
////        // Act
////        PlantPictureDto plantPicture = new PlantPictureDto();
////        plantPicture.setPlantPicture(mockPicture.getBytes());
////        PlantDto plantDto = new PlantDto()
////                .name("Rose")
////                .type("Flower")
////                .waterNeed("Medium")
////                .plantPicture(plantPicture);
////        ResponseEntity<?> response = plantController.addNewPlant(plantDto);
////
////        // Assert
////        assertEquals(HttpStatus.CREATED, response.getStatusCode());
////        assertNotNull(response.getBody());
////        assertInstanceOf(Plant.class, response.getBody());
////
////        Plant returnedPlant = (Plant) response.getBody();
////        assertEquals("Rose", returnedPlant.getName());
////        assertEquals("Flower", returnedPlant.getType());
////        assertEquals("Medium", returnedPlant.getWaterNeed());
////        assertEquals("High", returnedPlant.getSunlight());
////    }
////
////    @Test
////    void testCreatePlant_WithIOException() throws IOException {
////        // Arrange
////        MultipartFile faultyFile = new MockMultipartFile("plantPicture", "image.jpg", "image/jpeg", (byte[]) null);
////
////        // Act
////        PlantPictureDto plantPicture = new PlantPictureDto();
////        plantPicture.setPlantPicture(faultyFile.getBytes());
////        PlantDto plantDto = new PlantDto()
////                .name("Rose")
////                .type("Flower")
////                .waterNeed("Medium")
////                .plantPicture(plantPicture);
////        ResponseEntity<?> response = plantController.addNewPlant(plantDto);
////
////        // Assert
////        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
////        assertEquals("Failed to process plant picture", response.getBody());
////    }
//}
