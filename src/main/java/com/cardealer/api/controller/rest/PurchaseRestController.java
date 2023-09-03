package com.cardealer.api.controller.rest;

import com.cardealer.api.dto.CarPurchaseDTO;
import com.cardealer.api.dto.CarsToBuyDTO;
import com.cardealer.api.dto.InvoiceDTO;
import com.cardealer.api.dto.mapper.CarPurchaseMapper;
import com.cardealer.api.dto.mapper.InvoiceMapper;
import com.cardealer.business.CarPurchaseService;
import com.cardealer.domain.CarPurchaseRequest;
import com.cardealer.domain.Invoice;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(PurchaseRestController.API_PURCHASE)
public class PurchaseRestController {

    public static final String API_PURCHASE = "/api/purchase";

    private final CarPurchaseService carPurchaseService;
    private final CarPurchaseMapper carPurchaseMapper;
    private final InvoiceMapper invoiceMapper;

    @GetMapping
    public CarsToBuyDTO carsPurchaseData() {
        return CarsToBuyDTO.builder()
                .carsToBuy(carPurchaseService.availableCars().stream().map(carPurchaseMapper::map).toList())
                .build();
    }

    @PostMapping
    public InvoiceDTO makePurchase(@Valid @RequestBody CarPurchaseDTO carPurchaseDTO) {
        CarPurchaseRequest request = carPurchaseMapper.map(carPurchaseDTO);
        Invoice invoice = carPurchaseService.purchase(request);
        return invoiceMapper.map(invoice);
    }
}
