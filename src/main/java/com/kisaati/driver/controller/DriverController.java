package com.kisaati.driver.controller;

import com.kisaati.driver.model.Driver;
import com.kisaati.driver.model.dto.DriverListResponseDto;
import com.kisaati.driver.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping(value = "/drivers")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Driver>> getDrivers() throws IOException, ExecutionException, InterruptedException {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    private DriverListResponseDto getDtoFromList(List<Driver> allDrivers) {
        DriverListResponseDto driverListResponseDto = new DriverListResponseDto();
        driverListResponseDto.setDrivers(allDrivers);
        return  driverListResponseDto;
    }
}
