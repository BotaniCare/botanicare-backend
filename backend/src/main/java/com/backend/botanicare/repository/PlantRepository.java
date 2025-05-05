package com.backend.botanicare.repository;

import com.backend.botanicare.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
