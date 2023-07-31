package com.cardealer.business.dao;

import com.cardealer.domain.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceDAO {

    Optional<Service> findByServiceCode(String serviceCode);

    List<Service> findAll();
}
