package com.example.alacrity.api.services;

import com.example.alacrity.api.controllers.dto.CarDto;
import com.example.alacrity.api.controllers.dto.CarsDetailed;
import com.example.alacrity.api.entities.Car;
import com.example.alacrity.api.repositories.CarRepository;
import com.example.alacrity.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car getCar(Long carId) throws ResourceNotFoundException {
        return carRepository.findById(carId).orElseThrow(
                ResourceNotFoundException::new);
    }

    public Page<Car> getCars(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    // TODO: use streams later for less code
    public CarsDetailed getCarsDetailed(Pageable pageable) {
        Page<Car> carsPage = carRepository.findAll(pageable);
        List<Car> cars = carsPage.getContent();
        double sumOfPrice = 0;
        for (Car car: cars) {
            sumOfPrice += car.getPrice().doubleValue();
        }
        double averagePrice = sumOfPrice / cars.size();

        int oldestCarYear = 0;
        Car oldestCar = null;
        for (Car car: cars) {
            if (car.getYearOfManufacture() > oldestCarYear) {
                oldestCar = car;
                oldestCarYear = car.getYearOfManufacture();
            }
        }

        double mostExpensiveCarPrice = 0;
        Car mostExpensiveCar = null;
        for (Car car: cars) {
            if (car.getPrice().doubleValue() > mostExpensiveCarPrice) {
                mostExpensiveCar = car;
                mostExpensiveCarPrice = car.getPrice().doubleValue();
            }
        }

        CarsDetailed carsDetailed = new CarsDetailed();
        carsDetailed.setOldestCar(oldestCar);
        carsDetailed.setMostExpensiveCar(mostExpensiveCar);
        carsDetailed.setAveragePrice(averagePrice);
        return carsDetailed;
    }

    public Car createCar(CarDto carDto) {
        Car car = new Car();
        car.setMake(carDto.getMake());
        car.setPrice(BigDecimal.valueOf(carDto.getPrice()));
        car.setYearOfManufacture(carDto.getYearOfManufacture());
        return carRepository.save(car);
    }
}
