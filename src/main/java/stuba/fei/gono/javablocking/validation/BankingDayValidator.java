package stuba.fei.gono.javablocking.validation;

import stuba.fei.gono.javablocking.validation.annotations.BankingDay;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class BankingDayValidator implements ConstraintValidator<BankingDay, Date> {
    @Override
    public void initialize(BankingDay constraintAnnotation) {

    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
