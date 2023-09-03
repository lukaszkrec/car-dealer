package com.cardealer.business;

import com.cardealer.business.dao.ServiceDAO;
import com.cardealer.domain.Service;
import com.cardealer.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceCatalogService {

    private final ServiceDAO serviceDAO;

    @Transactional
    public Service findService(String serviceCode) {
        Optional<Service> service = serviceDAO.findByServiceCode(serviceCode);
        if (service.isEmpty()) {
            throw new NotFoundException("Could not find service by service code: [%s]".formatted(serviceCode));
        }
        return service.get();
    }

    public List<Service> findAll() {
        List<Service> services = serviceDAO.findAll();
        log.info("Available services: [{}]", services);
        return services;
    }
}
