package com.diseno.demo.fireBase.initial;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * date 2021-03-12 09:03
 *
 * @author Phạm Ngọc Thắng
 */
@Service
public class FCMInitializer {
    private static final Logger logger = LoggerFactory.getLogger(FCMInitializer.class);
    //parent
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize() {
        //parent
        logger.info("---------Start init firebase parent-----------");
        try {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options, "parent");
                logger.info("Firebase application of parent has been initialized");
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
