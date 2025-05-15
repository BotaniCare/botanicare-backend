//package com.backend.botanicare.service;
//
//import com.backend.botanicare.exceptions.RoomNotFoundException;
//import com.backend.botanicare.model.Plant;
//import com.backend.botanicare.model.Room;
//import com.backend.botanicare.repository.PlantRepository;
//import com.backend.botanicare.repository.RoomRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class RoomServiceTest {
//
//    @Mock
//    private RoomRepository roomRepository;
//
//    @Mock
//    private PlantRepository plantRepository;
//
//    @InjectMocks
//    private RoomService roomService;
//
//    private Room room;
//    private Plant plant;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//        room = new Room();
//        room.setRoomName("LivingRoom");
//        plant = new Plant();
//        plant.setPlantId(4);
//        plant.setName("Plant");
//        // room.setPlantIdsList(List.of(1, 2, 3));
//    }
//
//    @Test
//    void testGetAllRooms_ReturnsList() {
//        when(roomRepository.findAll()).thenReturn(List.of(room));
//
//        List<Room> rooms = roomService.getAllRooms();
//
//        assertEquals(1, rooms.size());
//        assertEquals("LivingRoom", rooms.get(0).getRoomName());
//    }
//
//    @Test
//    void testGetRoomByName_Found() {
//        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.of(room));
//
//        Room result = roomService.getRoomByName("LivingRoom");
//
//        assertNotNull(result);
//        assertEquals("LivingRoom", result.getRoomName());
//    }
//
//    @Test
//    void testGetRoomByName_NotFound() {
//        when(roomRepository.findById("UnknownRoom")).thenReturn(Optional.empty());
//
//
//        Exception ex = assertThrows(RoomNotFoundException.class,
//                () -> roomService.getRoomByName("UnknownRoom"));
//
//        assertEquals("UnknownRoom", ex.getMessage());
//
//    }
//
//    @Test
//    void testCreateRoom_Success() {
//        when(roomRepository.existsById("LivingRoom")).thenReturn(false);
//        when(roomRepository.save(any(Room.class))).thenReturn(room);
//
//        Room result = roomService.createRoom("LivingRoom");
//
//        assertNotNull(result);
//        assertEquals("LivingRoom", result.getRoomName());
//    }
//
//    @Test
//    void testCreateRoom_AlreadyExists() {
//        when(roomRepository.existsById("LivingRoom")).thenReturn(true);
//
//        Exception ex = assertThrows(IllegalArgumentException.class,
//                () -> roomService.createRoom("LivingRoom"));
//
//        assertEquals("Room already exists", ex.getMessage());
//    }
//
//    @Test
//    void testAddPlantToRoom_Success() {
//        int plantId = 4;
//        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.of(room));
//        when(plantRepository.existsById(plantId)).thenReturn(true);
//        when(plantRepository.findById(plantId)).thenReturn(Optional.of(plant));
//        when(roomRepository.save(any(Room.class))).thenReturn(room);
//
//        Room updated = roomService.addPlantToRoom("LivingRoom", plantId);
//
//        assertTrue(updated.getPlants().stream().map(Plant::getPlantId).toList().contains(plantId));
//    }
//
//    @Test
//    void testAddPlantToRoom_RoomNotFound() {
//        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.empty());
//
//        Exception ex = assertThrows(RoomNotFoundException.class,
//                () -> roomService.addPlantToRoom("LivingRoom", 4));
//
//        assertEquals("Room not found with name: LivingRoom", ex.getMessage());
//    }
//
//    @Test
//    void testRemovePlantFromRoom_Success() {
//        int plantId = 2;
//        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.of(room));
//        when(plantRepository.findById(plantId)).thenReturn(Optional.of(plant));
//        when(plantRepository.existsById(plantId)).thenReturn(true);
//        when(roomRepository.save(any(Room.class))).thenReturn(room);
//
//        Room updated = roomService.removePlantFromRoom("LivingRoom", plantId);
//
//        assertFalse(updated.getPlants().stream().map(Plant::getPlantId).toList().contains(plantId));
//    }
//
//    @Test
//    void testRemovePlantFromRoom_RoomNotFound() {
//        when(roomRepository.findById("LivingRoom")).thenReturn(Optional.empty());
//
//        Exception ex = assertThrows(RoomNotFoundException.class,
//                () -> roomService.removePlantFromRoom("LivingRoom", 2));
//
//        assertEquals("Room not found with name: LivingRoom", ex.getMessage());
//    }
//
//    @Test
//    void testDeleteRoom_Success() {
//        when(roomRepository.existsById("LivingRoom")).thenReturn(true);
//
//        roomService.deleteRoom("LivingRoom");
//
//        verify(roomRepository).deleteById("LivingRoom");
//    }
//
//    @Test
//    void testDeleteRoom_RoomNotFound() {
//        when(roomRepository.existsById("LivingRoom2")).thenReturn(false);
//
//        Exception ex = assertThrows(RoomNotFoundException.class,
//                () -> roomService.deleteRoom("LivingRoom2"));
//
//        assertEquals("Room not found with name: LivingRoom2", ex.getMessage());
//    }
//
//    @Test
//    void testSQLInjectionInRoomName_ThrowsException() {
//        String maliciousInput = "'; DROP TABLE room; --";
//
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
//                () -> roomService.createRoom(maliciousInput));
//
//        assertTrue(ex.getMessage().toLowerCase().contains("invalid"), "Expected invalid input message");
//    }
//}
