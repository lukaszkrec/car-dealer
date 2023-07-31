package com.cardealer.api.dto.mapper;

import org.mapstruct.Mapper;
import com.cardealer.api.dto.ServiceDTO;
import com.cardealer.domain.Service;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO map(Service service);
}
