package com.cardealer.api.dto.mapper;

import com.cardealer.api.dto.InvoiceDTO;
import com.cardealer.domain.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper extends OffsetDateTimeMapper {

    InvoiceDTO map(Invoice invoice);
}
