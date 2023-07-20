package com.mercado.java.mercadolivrojava.repository;

import com.mercado.java.mercadolivrojava.model.PurchaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<PurchaseModel, Integer> {
    Page<PurchaseModel> findPurchaseByCustomerId(Integer customerId, Pageable pageable);
}
