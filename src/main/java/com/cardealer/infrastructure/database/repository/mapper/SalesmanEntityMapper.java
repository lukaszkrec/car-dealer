package com.cardealer.infrastructure.database.repository.mapper;

import com.cardealer.domain.Salesman;
import com.cardealer.infrastructure.database.entity.SalesmanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalesmanEntityMapper {

    @Mapping(target = "invoices", ignore = true)
    Salesman mapFromEntity(SalesmanEntity entity);
}
