package com.backend.botanicare.service;

import com.backend.botanicare.exceptions.DeviceNotFoundException;
import com.backend.botanicare.exceptions.SendingMessageFailedException;
import com.backend.botanicare.model.Device;
import com.backend.botanicare.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final NotificationService notificationService;

    public Device addNewDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device getDeviceById(Integer id) {
        return deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("Device not found"));
    }

    public void deleteDeviceById(Integer id) {
        deviceRepository.deleteById(id);
    }

    public void triggerPushNotification(Integer deviceId) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new DeviceNotFoundException("Device not found"));
        String messagingToken = device.getDeviceMessagingToken();

        // Send push notification to messaging token
        try {
            notificationService.sendTestMessage(messagingToken);
        } catch (Exception e) {
            log.error("Failed sending the test notification", e);
            throw new SendingMessageFailedException("Failed sending the test notification");
        }
    }

}
