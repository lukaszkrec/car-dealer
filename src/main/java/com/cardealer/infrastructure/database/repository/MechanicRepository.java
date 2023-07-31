package com.cardealer.infrastructure.database.repository;

import com.cardealer.infrastructure.database.repository.jpa.MechanicJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.MechanicEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.cardealer.business.dao.MechanicDAO;
import com.cardealer.domain.Mechanic;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class MechanicRepository implements MechanicDAO {

    private final MechanicJpaRepository mechanicJpaRepository;
    private final MechanicEntityMapper mechanicEntityMapper;

    @Override
    public List<Mechanic> findAvailable() {
        return mechanicJpaRepository.findAll().stream()
            .map(mechanicEntityMapper::mapFromEntity)
            .toList();
    }

    @Override
    public Optional<Mechanic> findByPesel(String pesel) {
        return mechanicJpaRepository.findByPesel(pesel)
            .map(mechanicEntityMapper::mapFromEntity);
    }
}
