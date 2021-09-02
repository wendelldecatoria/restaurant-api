package com.wendecator.restaurant.controllers;

import com.wendecator.restaurant.dto.DTO;
import com.wendecator.restaurant.dto.SaleDTO;
import com.wendecator.restaurant.models.Item;
import com.wendecator.restaurant.models.Order;
import com.wendecator.restaurant.models.Sale;
import com.wendecator.restaurant.responses.RespondError;
import com.wendecator.restaurant.responses.RespondSuccess;
import com.wendecator.restaurant.services.ItemService;
import com.wendecator.restaurant.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("api")
public class SaleController {

    @Autowired
    SaleService saleService;
    @Autowired
    ItemService itemService;

    @RequestMapping("/sales")
    public ResponseEntity<Object> getAllSales() {
        try {
            List<Sale> salesList = saleService.getAllSales();
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", salesList);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping("sales/{id}")
    public ResponseEntity<Object> getSale(@PathVariable Long id) {
        try {
            Sale getSale = saleService.getSale(id).orElseThrow(() -> new Exception("No sale with id " + id + " found"));
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "OK", getSale);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sales")
    public ResponseEntity<Object> addSale(@Valid @DTO(SaleDTO.class) Sale sale) {
        // TODO: move the computation logic somewhere else
        Order order = sale.getOrder();

        // discount should be less than 1
        Integer discount = sale.getDiscount();

        // total amount computation
        Float totalAmount = 0f;

        // amount to be persisted
        Float total = 0f;

        List<Item> itemList = itemService.getItemsByOrderId(order.getId());

        for (Item item : itemList) {
            totalAmount += item.getMenu().getPrice();
        }

        if(!discount.equals(0)) {
            total = totalAmount - (totalAmount * discount);
        } else {
            total = totalAmount;
        }

        sale.setTotal(total);

        try {
            Sale saveSale = saleService.saveSale(sale);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Sale created successfully", saveSale);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/sales/{id}")
    public ResponseEntity<Object> updateSale(
            @Valid @DTO(SaleDTO.class) Sale sale,
            @PathVariable Long id) {
        try {
            saleService.getSale(id).orElseThrow(() -> new Exception("No sale with id " + id + " found"));
            Sale saveSale = saleService.saveSale(sale);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Sale updated successfully", saveSale);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/sales/{id}")
    public ResponseEntity<Object> patchSale(
            @Valid @DTO(SaleDTO.class) Sale sale,
            @PathVariable Long id) {
        try {
            saleService.getSale(id).orElseThrow(() -> new Exception("No sale with id " + id + " found"));
            Sale saveSale = saleService.saveSale(sale);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Sale updated successfully", saveSale);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/sales/{id}")
    public ResponseEntity<Object> deleteSale(@PathVariable Long id) {
        try {
            Sale getSale = saleService.getSale(id).orElseThrow(() -> new Exception("No sale with id " + id + " found"));
            saleService.deleteSale(getSale);
            return RespondSuccess.generateResponse(HttpStatus.OK, true, "Sale deleted successfully", null);
        } catch (Exception e) {
            return RespondError.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage());
        }
    }
}
