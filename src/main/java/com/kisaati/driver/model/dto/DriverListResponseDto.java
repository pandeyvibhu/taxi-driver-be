package com.kisaati.driver.model.dto;

import com.kisaati.driver.model.Driver;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DriverListResponseDto {
    List<Driver> drivers;
}
