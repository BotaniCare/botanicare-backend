package com.backend.botanicare.controller;

import com.backend.botanicare.model.Room;
import com.backend.botanicare.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<?> getAllRooms() {
        try {
            return ResponseEntity.ok(roomService.getAllRooms());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting Rooms");
        }
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<?> getRoomByName(@PathVariable String roomName) {
        Room room = roomService.getRoomByName(roomName);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestParam("roomName") String roomName) {
        if (roomName == null || roomName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Room name cannot be empty");
        }

        try {
            Room createdRoom = roomService.createRoom(roomName);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating Room");
        }
    }

    @PutMapping("/{roomName}/addPlant/{plantId}")
    public ResponseEntity<?> addPlantToRoom(@PathVariable String roomName, @PathVariable Integer plantId) {
        try {
            Room updatedRoom = roomService.addPlantToRoom(roomName, plantId);
            return ResponseEntity.ok(updatedRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding plant to Room");
        }
    }

    @PutMapping("/{roomName}/removePlant/{plantId}")
    public ResponseEntity<?> removePlantFromRoom(@PathVariable String roomName, @PathVariable Integer plantId) {
        try {
            Room updatedRoom = roomService.removePlantFromRoom(roomName, plantId);
            return ResponseEntity.ok(updatedRoom);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing plant from Room");
        }
    }

    @DeleteMapping("/{roomName}")
    public ResponseEntity<?> deleteRoom(@PathVariable String roomName) {
        try {
            roomService.deleteRoom(roomName);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }
}
