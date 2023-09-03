package com.cardealer.infrastructure.database.repository;

import com.cardealer.business.dao.SalesmanDAO;
import com.cardealer.domain.Salesman;
import com.cardealer.infrastructure.database.repository.jpa.SalesmanJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.SalesmanEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class SalesmanRepository implements SalesmanDAO {

    private final SalesmanJpaRepository salesmanJpaRepository;
    private final SalesmanEntityMapper salesmanEntityMapper;

    @Override
    public List<Salesman> findAvailable() {
        return salesmanJpaRepository.findAll().stream().map(salesmanEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<Salesman> findByPesel(String pesel) {
        return salesmanJpaRepository.findByPesel(pesel).map(salesmanEntityMapper::mapFromEntity);
    }
}
