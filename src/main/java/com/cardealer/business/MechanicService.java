package com.cardealer.business;

import com.cardealer.business.dao.MechanicDAO;
import com.cardealer.domain.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cardealer.domain.Mechanic;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class MechanicService {

    private final MechanicDAO mechanicDAO;

    @Transactional
    public List<Mechanic> findAvailable() {
        List<Mechanic> availableMechanics = mechanicDAO.findAvailable();
        log.info("Available salesmen: [{}]", availableMechanics.size());
        return availableMechanics;
    }

    @Transactional
    public Mechanic findMechanic(String pesel) {
        Optional<Mechanic> mechanic = mechanicDAO.findByPesel(pesel);
        if (mechanic.isEmpty()) {
            throw new NotFoundException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanic.get();
    }
}
