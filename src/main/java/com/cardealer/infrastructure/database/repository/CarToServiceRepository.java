package com.cardealer.infrastructure.database.repository;

import com.cardealer.business.dao.CarToServiceDAO;
import com.cardealer.domain.CarHistory;
import com.cardealer.domain.CarToService;
import com.cardealer.infrastructure.database.entity.CarToServiceEntity;
import com.cardealer.infrastructure.database.repository.jpa.CarToServiceJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.CarToServiceEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class CarToServiceRepository implements CarToServiceDAO {

    private final CarToServiceJpaRepository carToServiceJpaRepository;
    private final CarToServiceEntityMapper carToServiceEntityMapper;

    @Override
    public List<CarToService> findAll() {
        return carToServiceJpaRepository.findAll().stream().map(carToServiceEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<CarToService> findCarToServiceByVin(String vin) {
        return carToServiceJpaRepository.findOptionalByVin(vin).map(carToServiceEntityMapper::mapFromEntity);
    }

    @Override
    public CarToService saveCarToService(CarToService car) {
        CarToServiceEntity toSave = carToServiceEntityMapper.mapToEntity(car);
        CarToServiceEntity saved = carToServiceJpaRepository.save(toSave);
        return carToServiceEntityMapper.mapFromEntity(saved);
    }

    @Override
    public CarHistory findCarHistoryByVin(String vin) {
        CarToServiceEntity entity = carToServiceJpaRepository.findByVin(vin);
        return carToServiceEntityMapper.mapFromEntity(vin, entity);
    }
}
