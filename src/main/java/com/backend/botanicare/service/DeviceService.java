package com.backend.botanicare.service;

import com.backend.botanicare.exceptions.DeviceNotFoundException;
import com.backend.botanicare.model.Device;
import com.backend.botanicare.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public void addNewDevice(Device device) {
        deviceRepository.save(device);
    }

    public Device getDeviceById(Integer id) {
        return deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("Device not found"));
    }

    public void deleteDeviceById(Integer id) {
        deviceRepository.deleteById(id);
    }

}
