package com.mercado.java.mercadolivrojava.service;

import com.mercado.java.mercadolivrojava.enums.BookStatus;
import com.mercado.java.mercadolivrojava.enums.Errors;
import com.mercado.java.mercadolivrojava.events.PurchaseEvent;
import com.mercado.java.mercadolivrojava.exception.BookNotAvailableException;
import com.mercado.java.mercadolivrojava.model.BookModel;
import com.mercado.java.mercadolivrojava.model.PurchaseModel;
import com.mercado.java.mercadolivrojava.repository.PurchaseRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public PurchaseService(PurchaseRepository purchaseRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.purchaseRepository = purchaseRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void create(PurchaseModel purchaseModel) {
        for (BookModel book : purchaseModel.getBooks()) {
            if (book.getStatus() != BookStatus.ATIVO) {
                throw new BookNotAvailableException(
                        String.format(Errors.ML103.getMessage(), book.getId(), book.getStatus()), Errors.ML103.getCode()
                );
            }
        }
        purchaseRepository.save(purchaseModel);
        applicationEventPublisher.publishEvent(new PurchaseEvent(this, purchaseModel));
    }

    public void update(PurchaseModel purchaseModel) {
        purchaseRepository.save(purchaseModel);
    }

    public Page<PurchaseModel> getCustomerPurchases(Integer id, Pageable pageable) {
        return purchaseRepository.findPurchaseByCustomerId(id, pageable);
    }
}
