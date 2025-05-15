package com.backend.botanicare.controller;

import com.backend.botanicare.api.RoomsApi;
import com.backend.botanicare.exceptions.BadRoomNameException;
import com.backend.botanicare.mapper.RoomMapper;
import com.backend.botanicare.model.Room;
import com.backend.botanicare.model.RoomDto;
import com.backend.botanicare.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController implements RoomsApi {

    private final RoomService roomService;

    @Override
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomDto> roomDtos = RoomMapper.INSTANCE.toRoomDtos(rooms);
        return ResponseEntity.ok(roomDtos);
    }

    @Override
    public ResponseEntity<RoomDto> getRoomByName(String roomName) {
        Room room = roomService.getRoomByName(roomName);
        RoomDto roomDto = RoomMapper.INSTANCE.toRoomDto(room);
        return ResponseEntity.ok(roomDto);
    }

    @Override
    public ResponseEntity<Void> addNewRoom(RoomDto roomDto) {
        Room room = RoomMapper.INSTANCE.toRoom(roomDto);
        String roomName = room.getRoomName();

        if (roomName == null || roomName.trim().isEmpty()) {
            throw new BadRoomNameException("Room name cannot be empty or null");
        }

        roomService.createRoom(roomName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> addPlantToRoom(String roomName, Integer plantId) {
        roomService.addPlantToRoom(roomName, plantId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> removePlantFromRoom(String roomName, Integer plantId) {
        roomService.removePlantFromRoom(roomName, plantId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteRoomByRoomName(String roomName) {
        roomService.deleteRoom(roomName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
