package com.kisaati.driver.service;

import com.kisaati.driver.model.Driver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


public interface DriverService {
    List<Driver> getAllDrivers() throws ExecutionException, InterruptedException, IOException;
}
