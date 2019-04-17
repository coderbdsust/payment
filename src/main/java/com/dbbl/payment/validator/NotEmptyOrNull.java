package com.dbbl.payment.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotEmptyOrNullValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyOrNull {
    String message() default "Field can't be null or empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
