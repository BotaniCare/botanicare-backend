package com.backend.botanicare.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String roomName;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Plant> plants = new HashSet<>();

    public void addPlant(Plant plant) {
        plants.add(plant);
        plant.setRoom(this);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
        plant.setRoom(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
