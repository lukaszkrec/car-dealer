package com.cardealer.api.controller;

import com.cardealer.api.dto.mapper.CarMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cardealer.api.dto.CarPurchaseDTO;
import com.cardealer.api.dto.CarToBuyDTO;
import com.cardealer.api.dto.mapper.CarPurchaseMapper;
import com.cardealer.business.CarPurchaseService;
import com.cardealer.domain.CarPurchaseRequest;
import com.cardealer.domain.Invoice;
import com.cardealer.domain.Salesman;

import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PurchaseController {

    static final String PURCHASE = "/purchase";

    private final CarPurchaseService carPurchaseService;
    private final CarPurchaseMapper carPurchaseMapper;
    private final CarMapper carMapper;

    @GetMapping(value = PURCHASE)
    public ModelAndView carPurchasePage() {
        Map<String, ?> model = prepareCarPurchaseData();
        return new ModelAndView("car_purchase", model);
    }

    private Map<String, ?> prepareCarPurchaseData() {
        var availableCars = carPurchaseService.availableCars().stream()
            .map(carMapper::map)
            .toList();
        var availableCarVins = availableCars.stream()
            .map(CarToBuyDTO::getVin)
            .toList();
        var availableSalesmanPesels = carPurchaseService.availableSalesmen().stream()
            .map(Salesman::getPesel)
            .toList();
        return Map.of(
            "availableCarDTOs", availableCars,
            "availableCarVins", availableCarVins,
            "availableSalesmanPesels", availableSalesmanPesels,
            "carPurchaseDTO", CarPurchaseDTO.buildDefaultData()
        );
    }

    @PostMapping(value = PURCHASE)
    public String makePurchase(
        @Valid @ModelAttribute("carPurchaseDTO") CarPurchaseDTO carPurchaseDTO,
        ModelMap model
    ) {
        CarPurchaseRequest request = carPurchaseMapper.map(carPurchaseDTO);
        Invoice invoice = carPurchaseService.purchase(request);

        if (existingCustomerEmailExists(carPurchaseDTO.getExistingCustomerEmail())) {
            model.addAttribute("existingCustomerEmail", carPurchaseDTO.getExistingCustomerEmail());
        } else {
            model.addAttribute("customerName", carPurchaseDTO.getCustomerName());
            model.addAttribute("customerSurname", carPurchaseDTO.getCustomerSurname());
        }

        model.addAttribute("invoiceNumber", invoice.getInvoiceNumber());

        return "car_purchase_done";
    }

    private boolean existingCustomerEmailExists(String email) {
        return Objects.nonNull(email) && !email.isBlank();
    }
}
