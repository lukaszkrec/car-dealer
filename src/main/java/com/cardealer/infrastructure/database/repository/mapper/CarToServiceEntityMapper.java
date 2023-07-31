package com.cardealer.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.cardealer.domain.CarHistory;
import com.cardealer.domain.CarToService;
import com.cardealer.domain.Part;
import com.cardealer.domain.Service;
import com.cardealer.infrastructure.database.entity.CarToServiceEntity;
import com.cardealer.infrastructure.database.entity.ServiceMechanicEntity;
import com.cardealer.infrastructure.database.entity.ServicePartEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarToServiceEntityMapper {

    @Mapping(target = "carServiceRequests", ignore = true)
    CarToService mapFromEntity(CarToServiceEntity entity);

    default CarHistory mapFromEntity(String vin, CarToServiceEntity entity) {
        return CarHistory.builder()
            .carVin(vin)
            .carServiceRequests(entity.getCarServiceRequests().stream()
                .map(request -> CarHistory.CarServiceRequest.builder()
                    .carServiceRequestNumber(request.getCarServiceRequestNumber())
                    .receivedDateTime(request.getReceivedDateTime())
                    .completedDateTime(request.getCompletedDateTime())
                    .customerComment(request.getCustomerComment())
                    .services(request.getServiceMechanics().stream()
                        .map(ServiceMechanicEntity::getService)
                        .map(service -> Service.builder()
                            .serviceCode(service.getServiceCode())
                            .description(service.getDescription())
                            .price(service.getPrice())
                            .build())
                        .toList())
                    .parts(request.getServiceParts().stream()
                        .map(ServicePartEntity::getPart)
                        .map(service -> Part.builder()
                            .serialNumber(service.getSerialNumber())
                            .description(service.getDescription())
                            .price(service.getPrice())
                            .build())
                        .toList())
                    .build())
                .toList())
            .build();
    }

    CarToServiceEntity mapToEntity(CarToService car);
}