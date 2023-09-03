package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.SalesmanDTO;
import com.cardealer.domain.Salesman;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {

    SalesmanDTO map(final Salesman salesman);
}
