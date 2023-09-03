package com.cardealer.api.controller.rest;

import com.cardealer.api.dto.CarHistoryDTO;
import com.cardealer.api.dto.mapper.CarMapper;
import com.cardealer.business.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(CarHistoryRestController.API_CAR)
public class CarHistoryRestController {

    public static final String API_CAR = "/api/car";
    public static final String CAR_VIN_HISTORY = "/{carVin}/history";

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(value = CAR_VIN_HISTORY)
    public ResponseEntity<CarHistoryDTO> carHistory(@PathVariable String carVin) {
        if (Objects.isNull(carVin)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carMapper.map(carService.findCarHistoryByVin(carVin)));
    }
}
