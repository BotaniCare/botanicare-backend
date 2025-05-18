package com.backend.botanicare.controller;

import com.backend.botanicare.api.PlantsApi;
import com.backend.botanicare.mapper.PlantMapper;
import com.backend.botanicare.mapper.TaskMapper;
import com.backend.botanicare.model.Plant;
import com.backend.botanicare.model.PlantDto;
import com.backend.botanicare.model.Task;
import com.backend.botanicare.model.TaskDto;
import com.backend.botanicare.service.PlantService;
import com.backend.botanicare.service.WateringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlantController implements PlantsApi {

    private final PlantService plantService;

    @Override
    public ResponseEntity<List<PlantDto>> getAllPlants() {
        List<Plant> plants = plantService.getAllPlants();
        List<PlantDto> plantDtos = PlantMapper.INSTANCE.toPlantDtoList(plants);
        return ResponseEntity.ok(plantDtos);
    }

    @Override
    public ResponseEntity<PlantDto> getPlantById(Integer plantId) {
        Plant plant = plantService.getPlantById(plantId);
        PlantDto plantDto = PlantMapper.INSTANCE.toPlantDto(plant);
        return ResponseEntity.ok(plantDto);
    }

    @Override
    public ResponseEntity<byte[]> getPlantPictureByPlantId(Integer id) {
        Plant plant = plantService.getPlantById(id);
        byte[] picture = plant.getPlantPicture().getPlantPicture();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
    }

    
    @Override
    public ResponseEntity<PlantDto> updatePlant(Integer id, PlantDto plantDto) {
        Plant plant = PlantMapper.INSTANCE.toPlant(plantDto);
        Plant plantToUpdate = plantService.updatePlant(id, plant);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deletePlantById(Integer id) {
        plantService.deletePlant(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<TaskDto>> getTasksOfPlant(Integer id) {
        List<Task> tasks = plantService.getTasksOfPlant(id);
        List<TaskDto> taskDtos = TaskMapper.INSTANCE.toTaskDtoList(tasks);
        return ResponseEntity.ok(taskDtos);
    }

    @Override
    public ResponseEntity<Void> addTaskForPlant(Integer id, TaskDto taskDto) {
        Task task = TaskMapper.INSTANCE.toTask(taskDto);
        plantService.addTaskForPlant(id, task);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteTaskOfPlant(Integer id, Integer taskId) {
        plantService.deleteTaskOfPlant(id, taskId);
        return ResponseEntity.ok().build();
    }
}
