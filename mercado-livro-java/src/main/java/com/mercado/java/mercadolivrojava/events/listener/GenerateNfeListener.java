package com.mercado.java.mercadolivrojava.events.listener;

import com.mercado.java.mercadolivrojava.events.PurchaseEvent;
import com.mercado.java.mercadolivrojava.model.PurchaseModel;
import com.mercado.java.mercadolivrojava.service.PurchaseService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateNfeListener {

    private final PurchaseService purchaseService;

    public GenerateNfeListener(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @Async
    @EventListener
    public void listen(PurchaseEvent purchaseEvent) {
        String nfe = UUID.randomUUID().toString();
        PurchaseModel purchaseModel = new PurchaseModel(
                purchaseEvent.getPurchaseModel().getCustomerModel(),
                purchaseEvent.getPurchaseModel().getBooks(),
                nfe,
                purchaseEvent.getPurchaseModel().getPrice()
        );
        purchaseService.update(purchaseModel);
    }
}
