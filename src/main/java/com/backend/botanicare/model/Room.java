package com.backend.botanicare.model;

import jakarta.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Room {

    @Id
    @Column(name = "room_name")
    private String roomName;

    @Column(name = "plant_ids")
    private String plantIds;

    public Room() {}

    public Room(String roomName, List<Integer> plantIdsList) {
        this.roomName = roomName;
        setPlantIdsList(plantIdsList);
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<Integer> getPlantIdsList() {
        if (plantIds == null || plantIds.isBlank()) return new ArrayList<>();
        return Arrays.stream(plantIds.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void setPlantIdsList(List<Integer> plantIdsList) {
        this.plantIds = plantIdsList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public void addPlantId(Integer plantId) {
        List<Integer> ids = getPlantIdsList();
        if (!ids.contains(plantId)) {
            ids.add(plantId);
            setPlantIdsList(ids);
        }
    }

    public void removePlantId(Integer plantId) {
        List<Integer> ids = getPlantIdsList();
        ids.remove(plantId);
        setPlantIdsList(ids);
    }

    public String getRawPlantIds() {
        return plantIds;
    }
}
