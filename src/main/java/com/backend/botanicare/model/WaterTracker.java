package com.backend.botanicare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class WaterTracker {

    @Id
    private Integer plantId;

    private LocalDateTime plantWaterDate;

    public WaterTracker(int plantId, LocalDateTime localDateTime) {
    }
}
