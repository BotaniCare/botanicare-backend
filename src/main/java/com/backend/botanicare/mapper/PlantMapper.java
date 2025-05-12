package com.backend.botanicare.mapper;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.PlantDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlantMapper {

    PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);

    Plant toPlant(PlantDto plantDto);

    PlantDto toPlantDto(Plant plant);

    List<Plant> toPlantList(List<PlantDto> plantDtoList);

    List<PlantDto> toPlantDtoList(List<Plant> plantList);

}
