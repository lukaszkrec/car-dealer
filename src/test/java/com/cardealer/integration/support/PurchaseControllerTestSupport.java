package com.cardealer.integration.support;

import com.cardealer.api.dto.CarPurchaseDTO;
import com.cardealer.api.dto.CarsToBuyDTO;
import com.cardealer.api.dto.InvoiceDTO;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import com.cardealer.api.controller.rest.PurchaseRestController;

public interface PurchaseControllerTestSupport {

    RequestSpecification requestSpecification();

    default InvoiceDTO purchaseCar(final CarPurchaseDTO carPurchaseDTO) {
        return requestSpecification()
            .body(carPurchaseDTO)
            .post(PurchaseRestController.API_PURCHASE)
            .then()
            .statusCode(HttpStatus.OK.value())
            .and()
            .extract()
            .as(InvoiceDTO.class);
    }

    default CarsToBuyDTO findAvailableCars() {
        return requestSpecification()
            .get(PurchaseRestController.API_PURCHASE)
            .then()
            .statusCode(HttpStatus.OK.value())
            .and()
            .extract()
            .as(CarsToBuyDTO.class);
    }
}
