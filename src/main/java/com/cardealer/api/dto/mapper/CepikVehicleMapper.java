package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.CepikVehicleDTO;
import com.cardealer.domain.CepikVehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CepikVehicleMapper {

    CepikVehicleDTO map(CepikVehicle cepikVehicle);
}
