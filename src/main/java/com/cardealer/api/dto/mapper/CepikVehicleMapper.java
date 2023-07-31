package com.cardealer.api.dto.mapper;

import org.mapstruct.Mapper;
import com.cardealer.api.dto.CepikVehicleDTO;
import com.cardealer.domain.CepikVehicle;

@Mapper(componentModel = "spring")
public interface CepikVehicleMapper {

    CepikVehicleDTO map(CepikVehicle cepikVehicle);
}
