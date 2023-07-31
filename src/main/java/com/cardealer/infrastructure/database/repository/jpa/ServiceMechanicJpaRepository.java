package com.cardealer.infrastructure.database.repository.jpa;

import com.cardealer.infrastructure.database.entity.ServiceMechanicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMechanicJpaRepository extends JpaRepository<ServiceMechanicEntity, Integer> {


}
