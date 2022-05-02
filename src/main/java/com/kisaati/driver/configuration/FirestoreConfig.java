package com.kisaati.driver.configuration;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FirestoreConfig {

    @Value("${firestoreConfigPath}")
    private static String firestoreConfigPath;

    private static Firestore db;
    private FirestoreConfig() {}

    public static Firestore getInstance() {
        if(db != null) {
            return db;
        }
        InputStream serviceAccount = null;
        {
            try {
                serviceAccount = new FileInputStream(firestoreConfigPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        GoogleCredentials credentials = null;

        {
            try {
                credentials = GoogleCredentials.fromStream(serviceAccount);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FirebaseOptions options =  FirebaseOptions
                .builder()
                .setCredentials(credentials)
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
        return db;
    }

}
