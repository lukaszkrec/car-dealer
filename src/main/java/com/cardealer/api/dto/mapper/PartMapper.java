package com.cardealer.api.dto.mapper;

import org.mapstruct.Mapper;
import com.cardealer.api.dto.PartDTO;
import com.cardealer.domain.Part;

@Mapper(componentModel = "spring")
public interface PartMapper {

    PartDTO map(Part part);
}
