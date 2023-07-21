package com.mercado.java.mercadolivrojava.events;

import com.mercado.java.mercadolivrojava.model.PurchaseModel;
import org.springframework.context.ApplicationEvent;

public class PurchaseEvent extends ApplicationEvent {
    private final PurchaseModel purchaseModel;

    public PurchaseEvent(Object source, PurchaseModel purchaseModel){
        super(source);
        this.purchaseModel = purchaseModel;
    }

    public PurchaseModel getPurchaseModel(){
        return purchaseModel;
    }
}
