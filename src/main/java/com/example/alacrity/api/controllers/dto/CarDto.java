package com.example.alacrity.api.controllers.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CarDto {
    @NotBlank
    private String make;

    @NotNull
    @Min(1)
    private Double price;

    @NotNull
    @Min(1)
    private Integer yearOfManufacture;

}
