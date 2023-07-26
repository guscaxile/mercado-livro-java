package com.mercado.java.mercadolivrojava.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailAvailableValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EmailAvailable {
    String message() default "E-mail já cadastrado.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
