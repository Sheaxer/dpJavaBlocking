package stuba.fei.gono.javablocking.pojo;

import lombok.Data;
import stuba.fei.gono.javablocking.validation.annotations.MaxAmount;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Money {

    @NotNull
    private Currency currency;
    @MaxAmount(message = "FIELD_INVALID")
    private double amount;
}
