package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.CarPurchaseDTO;
import com.cardealer.api.dto.CarToBuyDTO;
import org.mapstruct.Mapper;
import com.cardealer.domain.CarPurchaseRequest;
import com.cardealer.domain.CarToBuy;

@Mapper(componentModel = "spring")
public interface CarPurchaseMapper {

    CarPurchaseRequest map(final CarPurchaseDTO dto);

    CarToBuyDTO map(CarToBuy a);
}
