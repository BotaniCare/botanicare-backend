package com.backend.botanicare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

}
