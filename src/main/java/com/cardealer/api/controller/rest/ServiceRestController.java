package com.cardealer.api.controller.rest;

import com.cardealer.api.dto.CarServiceCustomerRequestDTO;
import com.cardealer.api.dto.CarServiceRequestDTO;
import com.cardealer.api.dto.CarServiceRequestsDTO;
import com.cardealer.api.dto.mapper.CarServiceRequestMapper;
import com.cardealer.business.CarServiceRequestService;
import com.cardealer.domain.CarServiceRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ServiceRestController.API_SERVICE)
public class ServiceRestController {

    public static final String API_SERVICE = "/api/service";
    public static final String SERVICE_REQUEST = "/request";

    private final CarServiceRequestService carServiceRequestService;
    private final CarServiceRequestMapper carServiceRequestMapper;

    @PostMapping(value = SERVICE_REQUEST)
    public CarServiceRequestsDTO makeServiceRequest(
            @Valid @RequestBody CarServiceCustomerRequestDTO carServiceCustomerRequestDTO) {
        CarServiceRequest serviceRequest = carServiceRequestMapper.map(carServiceCustomerRequestDTO);
        carServiceRequestService.makeServiceRequest(serviceRequest);
        return CarServiceRequestsDTO.builder().carServiceRequests(getAvailableCarServiceRequests()).build();
    }

    private List<CarServiceRequestDTO> getAvailableCarServiceRequests() {
        return carServiceRequestService.availableServiceRequests().stream().map(carServiceRequestMapper::map).toList();
    }
}
