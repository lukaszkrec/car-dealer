package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.MechanicDTO;
import com.cardealer.domain.Mechanic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MechanicMapper {

    MechanicDTO map(final Mechanic mechanic);
}
