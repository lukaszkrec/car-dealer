package com.cardealer.infrastructure.database.repository;

import com.cardealer.infrastructure.database.entity.ServiceMechanicEntity;
import com.cardealer.infrastructure.database.repository.jpa.CarServiceRequestJpaRepository;
import com.cardealer.infrastructure.database.repository.jpa.PartJpaRepository;
import com.cardealer.infrastructure.database.repository.jpa.ServicePartJpaRepository;
import com.cardealer.infrastructure.database.repository.mapper.ServiceMechanicEntityMapper;
import com.cardealer.infrastructure.database.repository.mapper.ServicePartEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cardealer.business.dao.ServiceRequestProcessingDAO;
import com.cardealer.domain.CarServiceRequest;
import com.cardealer.domain.ServiceMechanic;
import com.cardealer.domain.ServicePart;
import com.cardealer.infrastructure.database.entity.CarServiceRequestEntity;
import com.cardealer.infrastructure.database.entity.PartEntity;
import com.cardealer.infrastructure.database.entity.ServicePartEntity;
import com.cardealer.infrastructure.database.repository.jpa.ServiceMechanicJpaRepository;

import java.util.Objects;


@Repository
@AllArgsConstructor
public class ServiceRequestProcessingRepository implements ServiceRequestProcessingDAO {

    private final ServiceMechanicJpaRepository serviceMechanicJpaRepository;
    private final CarServiceRequestJpaRepository carServiceRequestJpaRepository;
    private final PartJpaRepository partJpaRepository;
    private final ServicePartJpaRepository servicePartJpaRepository;
    private final ServiceMechanicEntityMapper serviceMechanicEntityMapper;
    private final ServicePartEntityMapper servicePartEntityMapper;

    @Override
    @Transactional
    public void process(
        CarServiceRequest serviceRequest,
        ServiceMechanic serviceMechanic
    ) {
        ServiceMechanicEntity serviceMechanicEntity = serviceMechanicEntityMapper.mapToEntity(serviceMechanic);
        serviceMechanicJpaRepository.saveAndFlush(serviceMechanicEntity);
        if (Objects.nonNull(serviceRequest.getCompletedDateTime())) {
            CarServiceRequestEntity carServiceRequestEntity = carServiceRequestJpaRepository
                .findById(serviceRequest.getCarServiceRequestId())
                .orElseThrow();
            carServiceRequestEntity.setCompletedDateTime(serviceRequest.getCompletedDateTime());
            carServiceRequestJpaRepository.saveAndFlush(carServiceRequestEntity);
        }
    }

    @Override
    @Transactional
    public void process(
        CarServiceRequest serviceRequest,
        ServiceMechanic serviceMechanic,
        ServicePart servicePart
    ) {
        PartEntity partEntity = partJpaRepository.findById(servicePart.getPart().getPartId()).orElseThrow();
        ServicePartEntity servicePartEntity = servicePartEntityMapper.mapToEntity(servicePart);
        servicePartEntity.setPart(partEntity);
        servicePartJpaRepository.saveAndFlush(servicePartEntity);
        process(serviceRequest, serviceMechanic);
    }
}
