package stuba.fei.gono.javablocking.validation.annotations;

import stuba.fei.gono.javablocking.validation.DaysBeforeDateValidator;
import stuba.fei.gono.javablocking.validation.MaxAmountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DaysBeforeDateValidator.class)
public @interface DaysBeforeDate {
    String message() default "";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    long days() default 3L;
}
