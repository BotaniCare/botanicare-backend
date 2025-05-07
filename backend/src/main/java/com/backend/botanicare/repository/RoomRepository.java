package com.backend.botanicare.repository;

import com.backend.botanicare.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}