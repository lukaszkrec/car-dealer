package com.cardealer.infrastructure.database.repository;

import com.cardealer.business.dao.CarToBuyDAO;
import com.cardealer.domain.CarToBuy;
import com.cardealer.infrastructure.database.repository.jpa.CarToBuyJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.CarToBuyEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarToBuyRepository implements CarToBuyDAO {

    private final CarToBuyJpaRepository carToBuyJpaRepository;
    private final CarToBuyEntityMapper carToBuyEntityMapper;

    @Override
    public List<CarToBuy> findAvailable() {
        return carToBuyJpaRepository.findAvailableCars().stream().map(carToBuyEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<CarToBuy> findCarToBuyByVin(String vin) {
        return carToBuyJpaRepository.findByVin(vin).map(carToBuyEntityMapper::mapFromEntity);
    }
}
