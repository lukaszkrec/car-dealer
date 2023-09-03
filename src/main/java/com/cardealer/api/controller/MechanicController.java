package com.cardealer.api.controller;

import com.cardealer.api.dto.*;
import com.cardealer.api.dto.mapper.CarServiceRequestMapper;
import com.cardealer.api.dto.mapper.MechanicMapper;
import com.cardealer.api.dto.mapper.PartMapper;
import com.cardealer.api.dto.mapper.ServiceMapper;
import com.cardealer.business.CarServiceProcessingService;
import com.cardealer.business.CarServiceRequestService;
import com.cardealer.business.PartCatalogService;
import com.cardealer.business.ServiceCatalogService;
import com.cardealer.domain.CarServiceProcessingRequest;
import com.cardealer.domain.Part;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MechanicController {

    public static final String MECHANIC = "/mechanic";
    public static final String MECHANIC_WORK_UNIT = "/mechanic/workUnit";

    private final CarServiceProcessingService carServiceProcessingService;
    private final CarServiceRequestService carServiceRequestService;
    private final PartCatalogService partCatalogService;
    private final ServiceCatalogService serviceCatalogService;
    private final CarServiceRequestMapper carServiceRequestMapper;
    private final MechanicMapper mechanicMapper;
    private final PartMapper partMapper;
    private final ServiceMapper serviceMapper;

    @GetMapping(value = MECHANIC)
    public ModelAndView mechanicCheckPage() {
        Map<String, ?> data = prepareNecessaryData();
        return new ModelAndView("mechanic_service", data);
    }

    private Map<String, ?> prepareNecessaryData() {
        var availableServiceRequests = getAvailableServiceRequests();
        var availableCarVins = availableServiceRequests.stream().map(CarServiceRequestDTO::getCarVin).toList();
        var availableMechanics = getAvailableMechanics();
        var availableMechanicPesels = availableMechanics.stream().map(MechanicDTO::getPesel).toList();
        var parts = findParts();
        var services = findServices();
        var partSerialNumbers = preparePartSerialNumbers(parts);
        var serviceCodes = services.stream().map(ServiceDTO::getServiceCode).toList();

        return Map.of("availableServiceRequestDTOs", availableServiceRequests, "availableCarVins", availableCarVins,
                "availableMechanicDTOs", availableMechanics, "availableMechanicPesels", availableMechanicPesels,
                "partDTOs", parts, "partSerialNumbers", partSerialNumbers, "serviceDTOs", services, "serviceCodes",
                serviceCodes, "carServiceProcessDTO", CarServiceMechanicProcessingUnitDTO.buildDefault());
    }

    @PostMapping(value = MECHANIC_WORK_UNIT)
    public String mechanicWorkUnit(
            @Valid @ModelAttribute("carServiceRequestProcessDTO") CarServiceMechanicProcessingUnitDTO dto,
            ModelMap modelMap) {
        CarServiceProcessingRequest request = carServiceRequestMapper.map(dto);
        carServiceProcessingService.process(request);
        if (dto.getDone()) {
            return "mechanic_service_done";
        } else {
            modelMap.addAllAttributes(prepareNecessaryData());
            return "redirect:/mechanic";
        }
    }

    private List<CarServiceRequestDTO> getAvailableServiceRequests() {
        return carServiceRequestService.availableServiceRequests().stream().map(carServiceRequestMapper::map).toList();
    }

    private List<MechanicDTO> getAvailableMechanics() {
        return carServiceRequestService.availableMechanics().stream().map(mechanicMapper::map).toList();
    }

    private List<PartDTO> findParts() {
        return partCatalogService.findAll().stream().map(partMapper::map).toList();
    }

    private List<ServiceDTO> findServices() {
        return serviceCatalogService.findAll().stream().map(serviceMapper::map).toList();
    }

    private List<String> preparePartSerialNumbers(List<PartDTO> parts) {
        List<String> partSerialNumbers = new ArrayList<>(parts.stream().map(PartDTO::getSerialNumber).toList());
        partSerialNumbers.add(Part.NONE);
        return partSerialNumbers;
    }
}
