package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.PartDTO;
import com.cardealer.domain.Part;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartMapper {

    PartDTO map(Part part);
}
