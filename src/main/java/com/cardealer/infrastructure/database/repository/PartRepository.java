package com.cardealer.infrastructure.database.repository;

import com.cardealer.infrastructure.database.repository.jpa.PartJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.PartEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import com.cardealer.business.dao.PartDAO;
import com.cardealer.domain.Part;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class PartRepository implements PartDAO {

    private final PartJpaRepository partJpaRepository;
    private final PartEntityMapper mapper;

    @Override
    public List<Part> findAll() {
        return partJpaRepository.findAll().stream()
            .map(mapper::mapFromEntity)
            .toList();
    }

    @Override
    public Optional<Part> findBySerialNumber(String serialNumber) {
        return partJpaRepository.findBySerialNumber(serialNumber)
            .map(mapper::mapFromEntity);
    }
}
