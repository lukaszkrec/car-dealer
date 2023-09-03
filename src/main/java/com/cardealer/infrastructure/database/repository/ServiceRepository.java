package com.cardealer.infrastructure.database.repository;

import com.cardealer.business.dao.ServiceDAO;
import com.cardealer.domain.Service;
import com.cardealer.infrastructure.database.repository.jpa.ServiceJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.ServiceEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class ServiceRepository implements ServiceDAO {

    private final ServiceJpaRepository serviceJpaRepository;
    private final ServiceEntityMapper mapper;

    @Override
    public List<Service> findAll() {
        return serviceJpaRepository.findAll().stream().map(mapper::mapFromEntity).toList();
    }

    @Override
    public Optional<Service> findByServiceCode(String serviceCode) {
        return serviceJpaRepository.findByServiceCode(serviceCode).map(mapper::mapFromEntity);
    }
}
