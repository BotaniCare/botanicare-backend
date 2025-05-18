package com.backend.botanicare;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseInitializer {

    private static final String ENV_KEY = "FIREBASE_SERVICE_ACCOUNT";

    @Bean
    public FirebaseAuth firebaseAuth() throws Exception {
        String jsonCredentials = System.getenv(ENV_KEY);

        if (jsonCredentials == null || jsonCredentials.isBlank()) {
            throw new IllegalStateException("Environment variable " + ENV_KEY + " is not set.");
        }

        try (InputStream serviceAccount = new ByteArrayInputStream(jsonCredentials.getBytes(StandardCharsets.UTF_8))) {
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
            }
        }

        return FirebaseAuth.getInstance();
    }
}

