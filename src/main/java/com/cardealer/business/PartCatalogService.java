package com.cardealer.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cardealer.business.dao.PartDAO;
import com.cardealer.domain.Part;
import com.cardealer.domain.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PartCatalogService {

    private final PartDAO partDAO;

    @Transactional
    public Part findPart(String partSerialNumber) {
        Optional<Part> part = partDAO.findBySerialNumber(partSerialNumber);
        if (part.isEmpty()) {
            throw new NotFoundException("Could not find part by part serial number: [%s]".formatted(partSerialNumber));
        }
        return part.get();
    }

    public List<Part> findAll() {
        List<Part> parts = partDAO.findAll();
        log.info("Available parts: [{}]", parts);
        return parts;
    }
}
