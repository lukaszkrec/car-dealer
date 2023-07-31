package com.cardealer.business.dao;

import com.cardealer.domain.CepikVehicle;

import java.time.LocalDate;
import java.util.List;

public interface CepikVehicleDao {

    List<CepikVehicle> getCepikVehicles(final LocalDate dateFrom, final LocalDate dateTo);

    CepikVehicle getCepikVehicle(final String vehicleId);
}
