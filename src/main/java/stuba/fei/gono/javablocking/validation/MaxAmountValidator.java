package stuba.fei.gono.javablocking.validation;

import lombok.extern.slf4j.Slf4j;
import stuba.fei.gono.javablocking.pojo.Money;
import stuba.fei.gono.javablocking.validation.annotations.MaxAmount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Slf4j
public class MaxAmountValidator implements ConstraintValidator<MaxAmount, Money> {

    private MaxAmount constraintAnnotation;

    @Override
    public void initialize(MaxAmount constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Money money, ConstraintValidatorContext constraintValidatorContext) {
        log.info("Checking");
        if(money.getAmount()<=0.0)
            return false;

        return !(money.getAmount() > constraintAnnotation.maxValue());
    }


}
