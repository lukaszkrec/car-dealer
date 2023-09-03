package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.ServiceDTO;
import com.cardealer.domain.Service;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO map(Service service);
}
