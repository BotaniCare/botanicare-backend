package com.backend.botanicare.repository;

import com.backend.botanicare.model.Device;
import com.backend.botanicare.model.WaterTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterTrackerRepository extends JpaRepository<WaterTracker, Integer> {
}
