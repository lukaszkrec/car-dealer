package com.cardealer.infrastructure.database.repository.jpa;

import com.cardealer.infrastructure.database.entity.MechanicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MechanicJpaRepository extends JpaRepository<MechanicEntity, Integer> {

    Optional<MechanicEntity> findByPesel(String pesel);
}
