package stuba.fei.gono.javablocking.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import stuba.fei.gono.javablocking.validation.annotations.ValidAccount;


@Data
@NoArgsConstructor
public class Account {


    private String iban;

    private String bic;
    private String localAccountNumber;

}
