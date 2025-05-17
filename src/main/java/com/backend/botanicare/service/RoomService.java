package com.backend.botanicare.service;

import com.backend.botanicare.exceptions.PlantNotFoundException;
import com.backend.botanicare.exceptions.RoomNotFoundException;
import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.Room;
import com.backend.botanicare.repository.PlantRepository;
import com.backend.botanicare.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final PlantRepository plantRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomByName(String roomName) {
        return roomRepository.findRoomByRoomName(roomName)
                .orElseThrow(() -> new RoomNotFoundException(roomName));
    }

    @Transactional
    public Room createRoom(String roomName) {
        if (roomName.contains(";") || roomName.contains("'") || roomName.contains("--")) {
            throw new IllegalArgumentException("Invalid input detected");
        }

        Room room = new Room();
        room.setRoomName(roomName);
        return roomRepository.save(room);
    }

    @Transactional
    public Room addPlantToRoom(String roomName, Integer plantId) {
        Room room = roomRepository.findById(roomName)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with name: " + roomName));

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException("Plant with ID " + plantId + " does not exist"));

        room.addPlant(plant);
        return roomRepository.save(room);
    }

    @Transactional
    public Room removePlantFromRoom(String roomName, Integer plantId) {
        Room room = roomRepository.findById(roomName)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with name: " + roomName));

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new PlantNotFoundException("Plant with ID " + plantId + " does not exist"));

        room.removePlant(plant);
        return roomRepository.save(room);
    }

    @Transactional
    public void deleteRoom(String roomName) {
        roomRepository.deleteRoomByRoomName(roomName);
    }
}
