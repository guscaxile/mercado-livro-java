package com.mercado.java.mercadolivrojava.events.listener;

import com.mercado.java.mercadolivrojava.events.PurchaseEvent;
import com.mercado.java.mercadolivrojava.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UpdateSoldBookListener {

    private final BookService bookService;

    public UpdateSoldBookListener(BookService bookService) {
        this.bookService = bookService;
    }

    @Async
    @EventListener
    public void listen(PurchaseEvent purchaseEvent){
        bookService.purchase(purchaseEvent.getPurchaseModel().getBooks());
    }
}
