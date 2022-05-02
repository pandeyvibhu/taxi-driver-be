package com.kisaati.driver.service.serviceImpl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.kisaati.driver.model.Driver;
import com.kisaati.driver.service.DriverService;
import org.springframework.stereotype.Service;
import static com.kisaati.driver.configuration.FirestoreConfig.getInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    @Override
    public List<Driver> getAllDrivers() throws ExecutionException, InterruptedException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Driver> drivers = new ArrayList<>();
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = getInstance().collection("drivers").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Map<String, Object> data = document.getData();
            Driver driver = mapper.convertValue(data, Driver.class);
            drivers.add(driver);
            System.out.println(document.getId() + " => " + document.toObject(Driver.class));
        }
        return drivers;
    }
}
