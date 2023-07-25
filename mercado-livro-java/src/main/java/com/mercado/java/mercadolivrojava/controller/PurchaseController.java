package com.mercado.java.mercadolivrojava.controller;

import com.mercado.java.mercadolivrojava.controller.mapper.PurchaseMapper;
import com.mercado.java.mercadolivrojava.controller.request.PostPurchaseRequest;
import com.mercado.java.mercadolivrojava.model.PurchaseModel;
import com.mercado.java.mercadolivrojava.service.PurchaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;

    public PurchaseController(PurchaseService purchaseService, PurchaseMapper purchaseMapper) {
        this.purchaseService = purchaseService;
        this.purchaseMapper = purchaseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void purchase(@RequestBody PostPurchaseRequest request){
        PurchaseModel purchaseModel = purchaseMapper.toModel(request);
        purchaseService.create(purchaseModel);
    }

    @GetMapping("/{id}")
    public Page<PurchaseModel> getCustomerPurchases(
            @PathVariable int id,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ){
        return purchaseService.getCustomerPurchases(id, pageable);
    }
}
