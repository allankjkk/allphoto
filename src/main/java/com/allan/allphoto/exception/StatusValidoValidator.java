package com.allan.allphoto.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class StatusValidoValidator implements ConstraintValidator<StatusValido, String> {

    private static final List<String> STATUS_PERMITIDOS = List.of("PENDENTE", "PAGO", "CANCELADO");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return STATUS_PERMITIDOS.contains(value.toUpperCase());
    }
}