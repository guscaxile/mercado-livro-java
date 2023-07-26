package com.mercado.java.mercadolivrojava.repository;

import com.mercado.java.mercadolivrojava.model.PurchaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseModel, Integer> {
    Page<PurchaseModel> findByCustomerModelId(Integer customerId, Pageable pageable);
}
