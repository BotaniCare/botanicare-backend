package com.backend.botanicare.controller;

import com.backend.botanicare.api.DevicesApi;
import com.backend.botanicare.mapper.DeviceMapper;
import com.backend.botanicare.model.Device;
import com.backend.botanicare.model.DeviceDto;
import com.backend.botanicare.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeviceController implements DevicesApi {

    private final DeviceService deviceService;

    @Override
    public ResponseEntity<Void> addNewDevice(DeviceDto deviceDto) {
        Device device = DeviceMapper.INSTANCE.toDevice(deviceDto);
        deviceService.addNewDevice(device);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DeviceDto> getDeviceById(Integer deviceId) {
        Device device = deviceService.getDeviceById(deviceId);
        DeviceDto dto = DeviceMapper.INSTANCE.toDeviceDto(device);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> deleteDeviceById(Integer deviceId) {
        deviceService.deleteDeviceById(deviceId);
        return ResponseEntity.ok().build();
    }
}
