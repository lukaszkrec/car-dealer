package com.cardealer.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.cardealer.api.dto.CarHistoryDTO;
import com.cardealer.api.dto.CarToBuyDTO;
import com.cardealer.api.dto.CarToServiceDTO;
import com.cardealer.domain.CarHistory;
import com.cardealer.domain.CarToBuy;
import com.cardealer.domain.CarToService;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper extends OffsetDateTimeMapper {

    CarToBuyDTO map(final CarToBuy car);

    CarToServiceDTO map(final CarToService car);

    @Mapping(source = "carServiceRequests", target = "carServiceRequests", qualifiedByName = "mapServiceRequests")
    CarHistoryDTO map(CarHistory carHistory);

    @SuppressWarnings("unused")
    @Named("mapServiceRequests")
    default List<CarHistoryDTO.ServiceRequestDTO> mapServiceRequests(
        List<CarHistory.CarServiceRequest> requests
    ) {
        return requests.stream().map(this::mapServiceRequest).toList();
    }

    @Mapping(source = "receivedDateTime", target = "receivedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    @Mapping(source = "completedDateTime", target = "completedDateTime", qualifiedByName = "mapOffsetDateTimeToString")
    CarHistoryDTO.ServiceRequestDTO mapServiceRequest(CarHistory.CarServiceRequest carServiceRequest);
}
