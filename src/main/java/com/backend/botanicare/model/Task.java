package com.backend.botanicare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plant_id")
    private Plant plant;


}
