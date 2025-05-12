package com.backend.botanicare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class PlantPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @Lob
    @Column(name = "plant_picture")
    @JsonIgnore
    private byte[] plantPicture;

    public Integer getPlantId() {
        return id;
    }

    public void setPlantId(Integer id) {
        this.id = id;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public byte[] getPlantPicture() {
        return plantPicture;
    }

    public void setPlantPicture(byte[] plantPicture) {
        this.plantPicture = plantPicture;
    }
}
