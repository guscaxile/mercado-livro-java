package com.mercado.java.mercadolivrojava.validation;

import com.mercado.java.mercadolivrojava.service.CustomerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAvailableValidator implements ConstraintValidator<EmailAvailable, String> {

    private final CustomerService customerService;

    public EmailAvailableValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return customerService.emailAvailable(value);
    }
}
