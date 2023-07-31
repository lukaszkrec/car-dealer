package com.cardealer.integration.rest;

import com.cardealer.api.dto.CarPurchaseDTO;
import com.cardealer.api.dto.CarsToBuyDTO;
import com.cardealer.api.dto.InvoiceDTO;
import com.cardealer.integration.configuration.RestAssuredIntegrationTestBase;
import org.junit.jupiter.api.Test;
import com.cardealer.integration.support.PurchaseControllerTestSupport;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseIT
    extends RestAssuredIntegrationTestBase
    implements PurchaseControllerTestSupport {

    @Test
    void thatCarPurchaseWorksCorrectly() {
        // given
        CarsToBuyDTO carsToBuyDTO = findAvailableCars();
        CarPurchaseDTO carPurchaseDTO = someCarPurchaseDTO();

        // when
        InvoiceDTO invoiceDTO = purchaseCar(carPurchaseDTO);

        // then
        CarsToBuyDTO carsToBuyDTOAfterPurchase = findAvailableCars();

        assertThat(invoiceDTO.getInvoiceNumber()).isNotNull();
        assertThat(invoiceDTO.getDateTime()).isNotNull();
        var carsToBuyBeforePurchase = new ArrayList<>(carsToBuyDTO.getCarsToBuy());
        var carsToBuyAfterPurchase = new ArrayList<>(carsToBuyDTOAfterPurchase.getCarsToBuy());
        carsToBuyBeforePurchase.removeAll(carsToBuyAfterPurchase);
        assertThat(carsToBuyBeforePurchase).hasSize(1);
        assertThat(carsToBuyBeforePurchase.get(0).getVin()).isEqualTo(carPurchaseDTO.getCarVin());
    }

    public static CarPurchaseDTO someCarPurchaseDTO() {
        return CarPurchaseDTO.buildDefaultData()
            .withCarVin("1FT7X2B60FEA74019")
            .withSalesmanPesel("73021314515");
    }
}
