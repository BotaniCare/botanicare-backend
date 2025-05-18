package com.backend.botanicare.service;

import com.backend.botanicare.model.Plant;
import com.backend.botanicare.repository.PlantRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final PlantRepository plantRepository;

    public NotificationService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public void sendNotification(Plant plant, String token) throws Exception {
        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        Notification
                                .builder()
                                .setTitle("Gießbenachrichtigung")
                                .setBody("Denk bitte dran, deine Pflanze " + plant.getName() + " zu gießen")
                                .build()
                )
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
    }

    // TODO: Add method to iterate over all available devices

}
