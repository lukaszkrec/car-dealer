package com.cardealer.api.dto.mapper;

import org.mapstruct.Mapper;
import com.cardealer.api.dto.MechanicDTO;
import com.cardealer.domain.Mechanic;

@Mapper(componentModel = "spring")
public interface MechanicMapper {

    MechanicDTO map(final Mechanic mechanic);
}
