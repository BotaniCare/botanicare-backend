package com.backend.botanicare.repository;

import com.backend.botanicare.model.PlantPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlantPictureRepository extends JpaRepository<PlantPicture, Integer> {
    Optional<PlantPicture> findByPlant_PlantId(Integer plantId);
}