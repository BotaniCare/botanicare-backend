package com.backend.botanicare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int plantId;

    private String name;
    private String type;
    private String waterNeed;
    private String sunlight;

    @OneToOne(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private PlantPicture plantPicture;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWaterNeed() {
        return waterNeed;
    }

    public void setWaterNeed(String waterNeed) {
        this.waterNeed = waterNeed;
    }

    public String getSunlight() {
        return sunlight;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public PlantPicture getPlantPicture() {
        return plantPicture;
    }

    public void setPlantPicture(PlantPicture plantPicture) {
        this.plantPicture = plantPicture;
    }
}
