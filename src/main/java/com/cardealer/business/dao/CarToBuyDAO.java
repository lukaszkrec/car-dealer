package com.cardealer.business.dao;

import com.cardealer.domain.CarToBuy;

import java.util.List;
import java.util.Optional;

public interface CarToBuyDAO {

    Optional<CarToBuy> findCarToBuyByVin(String vin);

    List<CarToBuy> findAvailable();

}
