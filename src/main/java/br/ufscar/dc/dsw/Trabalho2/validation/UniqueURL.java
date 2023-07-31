package br.ufscar.dc.dsw.Trabalho2.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueURL {
    String message() default "Um site ja foi cadastrado com essa URL.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
