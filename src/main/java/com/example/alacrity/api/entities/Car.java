package com.example.alacrity.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private BigDecimal price;
    private Integer yearOfManufacture;
}