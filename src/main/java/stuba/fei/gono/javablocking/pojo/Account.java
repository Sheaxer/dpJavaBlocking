package stuba.fei.gono.javablocking.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import stuba.fei.gono.javablocking.validation.annotations.ValidAccount;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {


    private String iban;

    private String bic;
    private String localAccountNumber;

}
