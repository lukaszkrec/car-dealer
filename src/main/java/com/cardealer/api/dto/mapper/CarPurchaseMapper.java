package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.CarPurchaseDTO;
import com.cardealer.api.dto.CarToBuyDTO;
import com.cardealer.domain.CarPurchaseRequest;
import com.cardealer.domain.CarToBuy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarPurchaseMapper {

    CarPurchaseRequest map(final CarPurchaseDTO dto);

    CarToBuyDTO map(CarToBuy a);
}
