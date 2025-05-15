//package com.backend.botanicare.controller;
//
//import com.backend.botanicare.model.Room;
//import com.backend.botanicare.service.RoomService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(RoomController.class)
//public class RoomControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private RoomService roomService;
//
////    @Test
////    public void testCreateRoom() throws Exception {
////        Room room = new Room();
////        when(roomService.createRoom("Living Room")).thenReturn(room);
////
////        mockMvc.perform(post("/rooms")
////                        .param("roomName", "Living Room"))
////                .andExpect(status().isCreated())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$.roomName").value("Living Room"))
////                .andExpect(jsonPath("$.plantIdsList").isArray());
////    }
////
////    @Test
////    public void testGetAllRooms() throws Exception {
////        Room room = new Room();
////        when(roomService.getAllRooms()).thenReturn(List.of(room));
////
////        mockMvc.perform(get("/rooms"))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$[0].roomName").value("Living Room"))
////                .andExpect(jsonPath("$[0].plantIdsList[0]").value(1))
////                .andExpect(jsonPath("$[0].plantIdsList[1]").value(2));
////    }
////
////    @Test
////    public void testGetRoomByName() throws Exception {
////        Room room = new Room();
////        when(roomService.getRoomByName("Living Room")).thenReturn(room);
////
////        mockMvc.perform(get("/rooms/Living Room"))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$.roomName").value("Living Room"))
////                .andExpect(jsonPath("$.plantIdsList").isArray());
////    }
////
////    @Test
////    public void testAddPlantToRoom() throws Exception {
////        Room room = new Room();
////        when(roomService.addPlantToRoom("Living Room", 1)).thenReturn(room);
////
////        mockMvc.perform(put("/rooms/Living Room/addPlant/1"))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$.plantIdsList[0]").value(1));
////    }
////
////    @Test
////    public void testRemovePlantFromRoom() throws Exception {
////        Room room = new Room();
////        when(roomService.removePlantFromRoom("Living Room", 1)).thenReturn(room);
////
////        mockMvc.perform(put("/rooms/Living Room/removePlant/1"))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(jsonPath("$.roomName").value("Living Room"))
////                .andExpect(jsonPath("$.plantIdsList").isArray());
////    }
////
////    @Test
////    public void testDeleteRoom() throws Exception {
////        doNothing().when(roomService).deleteRoom("Living Room");
////
////        mockMvc.perform(delete("/rooms/Living Room"))
////                .andExpect(status().isNoContent());
////    }
//}
