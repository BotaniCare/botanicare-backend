package com.backend.botanicare.service;

import com.backend.botanicare.model.Room;
import com.backend.botanicare.repository.RoomRepository;
import com.backend.botanicare.repository.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private PlantRepository plantRepository;

    private RoomService roomService;

    private Room room;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        roomService = new RoomService(roomRepository, plantRepository); // Incluir el mock de PlantRepository
        room = new Room();
        room.setRoomName("LivingRoom");
        room.setPlantIdsList(List.of(1, 2, 3));
    }

    @Test
    public void testGetAllRooms() {

        when(roomRepository.findAll()).thenReturn(List.of(room));

        List<Room> rooms = roomService.getAllRooms();

        assertNotNull(rooms);
        assertEquals(1, rooms.size());
        assertEquals("LivingRoom", rooms.get(0).getRoomName());
    }

    @Test
    public void testGetRoomByName() {
        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.of(room));

        Room foundRoom = roomService.getRoomByName("LivingRoom");

        assertNotNull(foundRoom);
        assertEquals("LivingRoom", foundRoom.getRoomName());
    }

    @Test
    public void testGetRoomByName_NotFound() {

        when(roomRepository.findById("NonExistingRoom")).thenReturn(Optional.empty());

        Room foundRoom = roomService.getRoomByName("NonExistingRoom");

        assertNull(foundRoom);
    }

    @Test
    public void testCreateRoom() {

        String roomName = "LivingRoom";
        when(roomRepository.existsById(roomName)).thenReturn(false);
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Room createdRoom = roomService.createRoom(roomName);

        assertNotNull(createdRoom);
        assertEquals("LivingRoom", createdRoom.getRoomName());
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void testCreateRoom_AlreadyExists() {

        String roomName = "LivingRoom";
        when(roomRepository.existsById(roomName)).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> roomService.createRoom(roomName));
    }

    @Test
    public void testAddPlantToRoom() {

        Integer plantId = 4;
        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.of(room));
        when(plantRepository.existsById(plantId)).thenReturn(true);
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Room updatedRoom = roomService.addPlantToRoom("LivingRoom", plantId);

        assertTrue(updatedRoom.getPlantIdsList().contains(plantId));
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void testAddPlantToRoom_RoomNotFound() {

        Integer plantId = 4;
        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> roomService.addPlantToRoom("LivingRoom", plantId));
    }

    @Test
    public void testRemovePlantFromRoom() {

        Integer plantId = 2;
        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.of(room));
        when(plantRepository.existsById(plantId)).thenReturn(true);
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Room updatedRoom = roomService.removePlantFromRoom("LivingRoom", plantId);

        assertFalse(updatedRoom.getPlantIdsList().contains(plantId));
        verify(roomRepository, times(1)).save(any(Room.class));
    }

    @Test
    public void testRemovePlantFromRoom_RoomNotFound() {

        Integer plantId = 2;
        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> roomService.removePlantFromRoom("LivingRoom", plantId));
    }

    @Test
    public void testDeleteRoom() {

        when(roomRepository.existsById("LivingRoom")).thenReturn(true);

        roomService.deleteRoom("LivingRoom");

        verify(roomRepository, times(1)).deleteById("LivingRoom");
    }

    @Test
    public void testDeleteRoom_RoomNotFound() {

        when(roomRepository.existsById("LivingRoom")).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> roomService.deleteRoom("LivingRoom"));
    }

    @Test
    void testSQLInjectionInRoomName() {

        String maliciousInput = "'; DROP TABLE room; --";

        System.out.println("Testing with malicious input: " + maliciousInput);
        assertThrows(IllegalArgumentException.class, () -> roomService.createRoom(maliciousInput),
                "Invalid input detected");
    }

}
