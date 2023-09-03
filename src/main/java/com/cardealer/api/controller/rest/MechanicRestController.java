package com.cardealer.api.controller.rest;

import com.cardealer.api.dto.CarServiceMechanicProcessingUnitDTO;
import com.cardealer.api.dto.CarServiceRequestDTO;
import com.cardealer.api.dto.CarServiceRequestsDTO;
import com.cardealer.api.dto.mapper.CarServiceRequestMapper;
import com.cardealer.business.CarServiceProcessingService;
import com.cardealer.business.CarServiceRequestService;
import com.cardealer.domain.CarServiceProcessingRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(MechanicRestController.API_MECHANIC)
public class MechanicRestController {

    public static final String API_MECHANIC = "/api/mechanic";
    public static final String AVAILABLE_SERVICE_REQUESTS = "/availableServiceRequests";
    public static final String MECHANIC_WORK_UNIT = "/workUnit";

    private final CarServiceRequestService carServiceRequestService;
    private final CarServiceProcessingService carServiceProcessingService;
    private final CarServiceRequestMapper carServiceRequestMapper;

    @GetMapping(value = AVAILABLE_SERVICE_REQUESTS)
    public CarServiceRequestsDTO availableServiceRequests() {
        return getCarServiceRequestsDTO();
    }

    @PostMapping(value = MECHANIC_WORK_UNIT)
    public CarServiceRequestsDTO mechanicPerformWorkUnit(
            @Valid @RequestBody CarServiceMechanicProcessingUnitDTO processingUnitDTO) {
        CarServiceProcessingRequest request = carServiceRequestMapper.map(processingUnitDTO);
        carServiceProcessingService.process(request);
        return getCarServiceRequestsDTO();
    }

    private CarServiceRequestsDTO getCarServiceRequestsDTO() {
        return CarServiceRequestsDTO.builder().carServiceRequests(getAvailableCarServiceRequests()).build();
    }

    private List<CarServiceRequestDTO> getAvailableCarServiceRequests() {
        return carServiceRequestService.availableServiceRequests().stream().map(carServiceRequestMapper::map).toList();
    }
}
