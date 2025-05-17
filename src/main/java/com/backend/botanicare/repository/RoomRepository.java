package com.backend.botanicare.repository;

import com.backend.botanicare.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    Integer deleteRoomByRoomName(String roomName);
    Optional<Room> findRoomByRoomName(String roomName);
}