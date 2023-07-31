package com.cardealer.api.dto.mapper;

import org.mapstruct.Mapper;
import com.cardealer.api.dto.InvoiceDTO;
import com.cardealer.domain.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper extends OffsetDateTimeMapper {

    InvoiceDTO map(Invoice invoice);
}
