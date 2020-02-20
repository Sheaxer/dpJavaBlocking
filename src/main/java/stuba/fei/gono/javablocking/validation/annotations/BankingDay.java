package stuba.fei.gono.javablocking.validation.annotations;

import stuba.fei.gono.javablocking.validation.BankingDayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = BankingDayValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface BankingDay {
    String message() default "";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
