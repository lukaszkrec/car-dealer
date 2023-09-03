package com.cardealer.api.controller;

import com.cardealer.api.dto.mapper.CarMapper;
import com.cardealer.api.dto.mapper.MechanicMapper;
import com.cardealer.api.dto.mapper.SalesmanMapper;
import com.cardealer.business.CarPurchaseService;
import com.cardealer.business.CarServiceRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SalesmanController {

    private static final String SALESMAN = "/salesman";

    private final CarPurchaseService carPurchaseService;
    private final CarServiceRequestService carServiceRequestService;
    private final CarMapper carMapper;
    private final SalesmanMapper salesmanMapper;
    private final MechanicMapper mechanicMapper;

    @GetMapping(value = SALESMAN)
    public String homePage(Model model) {
        var availableCars = carPurchaseService.availableCars().stream().map(carMapper::map).toList();
        var availableSalesmen = carPurchaseService.availableSalesmen().stream().map(salesmanMapper::map).toList();
        var availableMechanics = carServiceRequestService.availableMechanics()
                .stream()
                .map(mechanicMapper::map)
                .toList();

        model.addAttribute("availableCarDTOs", availableCars);
        model.addAttribute("availableSalesmenDTOs", availableSalesmen);
        model.addAttribute("availableMechanicDTOs", availableMechanics);

        return "salesman_portal";
    }
}
