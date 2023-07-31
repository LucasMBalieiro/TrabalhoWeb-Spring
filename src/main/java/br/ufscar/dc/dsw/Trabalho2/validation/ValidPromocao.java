package br.ufscar.dc.dsw.Trabalho2.validation;

import br.ufscar.dc.dsw.Trabalho2.validation.UniqueDateValidation;

import javax.swing.text.Element;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueDateValidation.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPromocao {
    String message() default "There is already a promotion registered in that hotel for that period";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
