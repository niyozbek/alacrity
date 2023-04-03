package com.example.alacrity.api.repositories;

import com.example.alacrity.api.entities.Car;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
}