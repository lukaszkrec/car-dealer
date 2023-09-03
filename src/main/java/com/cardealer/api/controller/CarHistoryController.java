package com.cardealer.api.controller;

import com.cardealer.api.dto.CarHistoryDTO;
import com.cardealer.api.dto.CarToServiceDTO;
import com.cardealer.api.dto.mapper.CarMapper;
import com.cardealer.business.CarService;
import com.cardealer.domain.CarHistory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class CarHistoryController {

    private static final String CAR_HISTORY = "/car/history";

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(value = CAR_HISTORY)
    public String carHistory(@RequestParam(value = "carVin", required = false) String carVin, Model model) {
        var allCars = carService.findAllCarsWithHistory().stream().map(carMapper::map).toList();
        var allCarVins = allCars.stream().map(CarToServiceDTO::getVin).toList();

        model.addAttribute("allCarDTOs", allCars);
        model.addAttribute("allCarVins", allCarVins);

        if (Objects.nonNull(carVin)) {
            CarHistory carHistory = carService.findCarHistoryByVin(carVin);
            model.addAttribute("carHistoryDTO", carMapper.map(carHistory));
        } else {
            model.addAttribute("carHistoryDTO", CarHistoryDTO.buildDefault());
        }
        return "car_history";
    }
}
