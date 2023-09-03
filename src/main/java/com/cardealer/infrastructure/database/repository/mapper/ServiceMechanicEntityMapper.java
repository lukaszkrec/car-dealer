package com.cardealer.infrastructure.database.repository.mapper;

import com.cardealer.domain.ServiceMechanic;
import com.cardealer.infrastructure.database.entity.ServiceMechanicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMechanicEntityMapper {

    ServiceMechanicEntity mapToEntity(ServiceMechanic serviceMechanic);
}
