package com.cardealer.business.dao;

import com.cardealer.domain.CarServiceRequest;

import java.util.List;
import java.util.Set;

public interface CarServiceRequestDAO {
    List<CarServiceRequest> findAvailable();

    Set<CarServiceRequest> findActiveServiceRequestsByCarVin(String carVin);
}
