package com.cardealer.api.dto.mapper;

import org.mapstruct.Mapper;
import com.cardealer.api.dto.SalesmanDTO;
import com.cardealer.domain.Salesman;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {

    SalesmanDTO map(final Salesman salesman);
}
