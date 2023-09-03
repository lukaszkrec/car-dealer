package com.cardealer.infrastructure.database.repository.mapper;

import com.cardealer.domain.CarToBuy;
import com.cardealer.infrastructure.database.entity.CarToBuyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarToBuyEntityMapper {

    @Mapping(target = "invoice", ignore = true)
    CarToBuy mapFromEntity(CarToBuyEntity entity);
}
