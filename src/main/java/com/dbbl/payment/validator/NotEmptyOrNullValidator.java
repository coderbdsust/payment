package com.dbbl.payment.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyOrNullValidator implements
        ConstraintValidator<NotEmptyOrNull, String> {

    @Override
    public void initialize(NotEmptyOrNull object) {
    }

    @Override
    public boolean isValid(String field,
                           ConstraintValidatorContext cxt) {
        return field != null && !field.isEmpty();
    }
}
