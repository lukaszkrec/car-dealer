package com.cardealer.infrastructure.database.repository.mapper;

import com.cardealer.domain.Invoice;
import com.cardealer.infrastructure.database.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceEntityMapper {

    InvoiceEntity mapToEntity(Invoice invoice);
}
