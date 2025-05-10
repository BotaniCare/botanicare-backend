package com.backend.botanicare.controller;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PlantControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantController plantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlant() throws IOException {
        MultipartFile mockPicture = new MockMultipartFile("plantPicture", "image.jpg", "image/jpeg", new byte[1]);

        Plant mockPlant = new Plant();
        mockPlant.setName("Rose");
        mockPlant.setType("Flower");
        mockPlant.setWaterNeed("Medium");
        mockPlant.setSunlight("High");

        when(plantService.createPlant(any(Plant.class), any(byte[].class))).thenReturn(mockPlant);

        ResponseEntity<?> response = plantController.createPlant("Rose", "Flower", "Medium", "High", mockPicture);

        assertNotNull(response.getBody(), "The response body should not be null");
        Plant returnedPlant = (Plant) response.getBody();
        assertEquals("Rose", returnedPlant.getName());
        assertEquals("Flower", returnedPlant.getType());
        assertEquals("Medium", returnedPlant.getWaterNeed());
        assertEquals("High", returnedPlant.getSunlight());
    }
}