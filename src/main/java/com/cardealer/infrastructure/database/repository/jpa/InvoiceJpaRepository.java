package com.cardealer.infrastructure.database.repository.jpa;

import com.cardealer.infrastructure.database.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Integer> {


}
