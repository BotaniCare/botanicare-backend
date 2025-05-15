package com.backend.botanicare.mapper;

import com.backend.botanicare.model.Device;
import com.backend.botanicare.model.DeviceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeviceMapper {

    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    Device toDevice(DeviceDto deviceDto);

    DeviceDto toDeviceDto(Device device);

}
