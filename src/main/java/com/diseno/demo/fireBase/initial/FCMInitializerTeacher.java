package com.diseno.demo.fireBase.initial;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * date 2021-03-12 09:02
 *
 * @author Phạm Ngọc Thắng
 */
@Service
public class FCMInitializerTeacher {
    private static final Logger logger = LoggerFactory.getLogger(FCMInitializerTeacher.class);

    @Value("${app.firebase-teacher-file}")
    private String firebaseConfigPath;

    @Value("${app.firebase-plus-file}")
    private String firebaseConfigPathPlus;

    @Bean
    public void initialize() {
        logger.info("---------Start init firebase teacher------------");
        try {
            FirebaseOptions optionsTeacher = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();

            FirebaseApp.initializeApp(optionsTeacher, "teacher");
            logger.info("Firebase application of teacher has been initialized");

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


    @Bean
    public void initializePlus() {
        logger.info("-----------Start init firebase plus-------------");
        try {
            FirebaseOptions optionsTeacher = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPathPlus).getInputStream())).build();

            FirebaseApp.initializeApp(optionsTeacher, "plus");
            logger.info("Firebase application of plus has been initialized");

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
