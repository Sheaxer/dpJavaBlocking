package stuba.fei.gono.javablocking.validation;

import stuba.fei.gono.javablocking.pojo.Money;
import stuba.fei.gono.javablocking.pojo.ReportedOverlimitTransaction;
import stuba.fei.gono.javablocking.pojo.Vault;
import stuba.fei.gono.javablocking.validation.annotations.ValidVault;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VaultValidator implements ConstraintValidator<ValidVault,ReportedOverlimitTransaction> {
    @Override
    public void initialize(ValidVault constraintAnnotation) {

    }

    @Override
    public boolean isValid(ReportedOverlimitTransaction reportedOverlimitTransaction, ConstraintValidatorContext constraintValidatorContext) {

       Money m  = reportedOverlimitTransaction.getAmount();
       double value=0.0;
       for(Vault v: reportedOverlimitTransaction.getVault())
       {
           value+=v.getNominalValue();
       }
        return value == m.getAmount();
    }
}
