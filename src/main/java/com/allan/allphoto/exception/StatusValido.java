package com.allan.allphoto.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusValido {

    String message() default "Status inválido! Use: PENDENTE, PAGO ou CANCELADO";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}