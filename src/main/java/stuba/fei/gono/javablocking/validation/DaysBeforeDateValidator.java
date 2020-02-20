package stuba.fei.gono.javablocking.validation;

import lombok.extern.slf4j.Slf4j;
import stuba.fei.gono.javablocking.validation.annotations.DaysBeforeDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@Slf4j
public class DaysBeforeDateValidator implements ConstraintValidator<DaysBeforeDate, Date> {

    private Date today;
    private long days;

    @Override
    public void initialize(DaysBeforeDate constraintAnnotation) {
        this.today = new Date();
        days = constraintAnnotation.days();
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        long diff = date.getTime() - today.getTime();
        //System.out.println(diff);
        log.info(String.valueOf(diff));
        if(diff <0)
            return true;
        if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= days)
            return true;
        return false;
    }
}
