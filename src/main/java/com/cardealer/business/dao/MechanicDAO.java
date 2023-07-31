package com.cardealer.business.dao;

import com.cardealer.domain.Mechanic;

import java.util.List;
import java.util.Optional;

public interface MechanicDAO {

    List<Mechanic> findAvailable();

    Optional<Mechanic> findByPesel(String pesel);
}
