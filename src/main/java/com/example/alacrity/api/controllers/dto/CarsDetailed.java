package com.example.alacrity.api.controllers.dto;

import com.example.alacrity.api.entities.Car;
import lombok.Data;

@Data
public class CarsDetailed {
    private double averagePrice;
    private Car mostExpensiveCar;
    private Car oldestCar;
}
