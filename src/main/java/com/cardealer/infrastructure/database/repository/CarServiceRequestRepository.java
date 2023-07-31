package com.cardealer.infrastructure.database.repository;

import com.cardealer.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.CarServiceRequestEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.cardealer.business.dao.CarServiceRequestDAO;
import com.cardealer.domain.CarServiceRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CarServiceRequestRepository implements CarServiceRequestDAO {

    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final CarServiceRequestEntityMapper carServiceRequestEntityMapper;

    @Override
    public List<CarServiceRequest> findAvailable() {
        return carServiceRequestJpaRepository.findAllByCompletedDateTimeIsNull().stream()
            .map(carServiceRequestEntityMapper::mapFromEntityWithCar)
            .toList();
    }

    @Override
    public Set<CarServiceRequest> findActiveServiceRequestsByCarVin(String carVin) {
        return carServiceRequestJpaRepository.findActiveServiceRequestsByCarVin(carVin).stream()
            .map(carServiceRequestEntityMapper::mapFromEntity)
            .collect(Collectors.toSet());
    }
}
