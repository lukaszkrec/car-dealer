package com.cardealer.infrastructure.database.repository.mapper;

import com.cardealer.domain.ServicePart;
import com.cardealer.infrastructure.database.entity.ServicePartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServicePartEntityMapper {

    ServicePartEntity mapToEntity(ServicePart servicePart);
}
