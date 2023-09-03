package com.cardealer.infrastructure.database.repository.mapper;

import com.cardealer.domain.Part;
import com.cardealer.infrastructure.database.entity.PartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartEntityMapper {

    @Mapping(target = "serviceParts", ignore = true)
    Part mapFromEntity(PartEntity entity);
}
