package com.backend.botanicare.mapper;

import com.backend.botanicare.model.Room;
import com.backend.botanicare.model.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    Room toRoom(RoomDto roomDto);

    RoomDto toRoomDto(Room room);

    List<Room> toRooms(List<RoomDto> roomDtos);

    List<RoomDto> toRoomDtos(List<Room> rooms);

}
