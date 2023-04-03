package com.example.alacrity.api.controllers;

import com.example.alacrity.api.controllers.dto.CarDto;
import com.example.alacrity.api.controllers.dto.CarsDetailed;
import com.example.alacrity.api.entities.Car;
import com.example.alacrity.api.services.CarService;
import com.example.alacrity.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public Page<Car> getCars(@RequestParam(defaultValue = "0") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<Car> submissionList = carService
                .getCars(pageable);
        return submissionList;
    }

    @GetMapping("detailed")
    public CarsDetailed getCarsDetailed(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        return carService.getCarsDetailed(pageable);
    }

    @GetMapping("{id}")
    public Car getCar(@PathVariable Long id)
            throws ResourceNotFoundException {
        return carService.getCar(id);
    }

    @PostMapping
    public Car createCar(@Valid @RequestBody CarDto carDto) {
        return carService.createCar(carDto);
    }
}
