package com.backend.botanicare.service;

import com.backend.botanicare.model.Room;
import com.backend.botanicare.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomByName(String roomName) {
        return roomRepository.findById(roomName).orElse(null);
    }

    @Transactional
    public Room createRoom(String roomName) {
        if (roomRepository.existsById(roomName)) {
            throw new IllegalArgumentException("Room already exists");
        }

        if (roomName.contains(";") || roomName.contains("'") || roomName.contains("--")) {
            throw new IllegalArgumentException("Invalid input detected");
        }

        Room room = new Room();
        room.setRoomName(roomName);
        room.setPlantIdsList(List.of());
        return roomRepository.save(room);
    }

    @Transactional
    public Room addPlantToRoom(String roomName, Integer plantId) {
        Room room = roomRepository.findById(roomName)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with name: " + roomName));

        room.addPlantId(plantId);
        return roomRepository.save(room);
    }

    @Transactional
    public Room removePlantFromRoom(String roomName, Integer plantId) {
        Room room = roomRepository.findById(roomName)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with name: " + roomName));

        room.removePlantId(plantId);
        return roomRepository.save(room);
    }

    @Transactional
    public void deleteRoom(String roomName) {
        if (roomRepository.existsById(roomName)) {
            roomRepository.deleteById(roomName);
        } else {
            throw new IllegalArgumentException("Room not found with name: " + roomName);
        }
    }
}

