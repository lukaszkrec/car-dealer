package com.cardealer.business.dao;

import com.cardealer.domain.CarServiceRequest;
import com.cardealer.domain.ServiceMechanic;
import com.cardealer.domain.ServicePart;

public interface ServiceRequestProcessingDAO {
    void process(CarServiceRequest serviceRequest, ServiceMechanic serviceMechanic);

    void process(CarServiceRequest serviceRequest, ServiceMechanic serviceMechanic, ServicePart servicePart);
}
