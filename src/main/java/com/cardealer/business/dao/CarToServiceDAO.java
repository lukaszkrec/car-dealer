package com.cardealer.business.dao;

import com.cardealer.domain.CarHistory;
import com.cardealer.domain.CarToService;

import java.util.List;
import java.util.Optional;

public interface CarToServiceDAO {

    Optional<CarToService> findCarToServiceByVin(String vin);

    CarToService saveCarToService(CarToService car);

    CarHistory findCarHistoryByVin(String vin);

    List<CarToService> findAll();
}
