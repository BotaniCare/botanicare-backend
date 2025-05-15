package com.backend.botanicare.mapper;

import com.backend.botanicare.model.Task;
import com.backend.botanicare.model.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toTask(TaskDto task);

    TaskDto toTaskDto(Task task);

    List<Task> toTaskList(List<TaskDto> taskDtoList);

    List<TaskDto> toTaskDtoList(List<Task> taskList);

}
