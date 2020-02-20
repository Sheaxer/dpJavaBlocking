package stuba.fei.gono.javablocking.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import stuba.fei.gono.javablocking.data.ClientDeserializer;
import stuba.fei.gono.javablocking.data.EmployeeDeserializer;
import stuba.fei.gono.javablocking.data.OrganisationUnitDeserializer;
import stuba.fei.gono.javablocking.data.ZonedDateTimeDeserializer;
import stuba.fei.gono.javablocking.validation.annotations.BankingDay;
import stuba.fei.gono.javablocking.validation.annotations.DaysBeforeDate;
import stuba.fei.gono.javablocking.validation.annotations.MaxAmount;
import stuba.fei.gono.javablocking.validation.annotations.ValidAccount;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ReportedOverlimitTransaction {

    @Id
    private String id;
    @NotNull
    private OrderCategory orderCategory;

    private State state;
    @NotNull
    @ValidAccount
    private Account sourceAccount;

    @DBRef
    @NotNull
    @Valid
    @JsonDeserialize(using = ClientDeserializer.class)
    private Client clientId;
    @NotBlank
    private String identificationId;
    @NotNull
    @MaxAmount(message = "FIELD_INVALID")
    private Money amount;
    @NotNull
    @NotEmpty
    private List<Vault> vault;
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime modificationDate;


    //@Past(message = "INVALID_DATE_IN_PAST")
    //@BankingDay(message = "INVALID_DATE")
    @NotNull
    @FutureOrPresent(message = "INVALID_DATE_IN_PAST")
    @DaysBeforeDate(message = "FIELD_INVALID_TOO_NEAR_IN_FUTURE")
    private Date transferDate;

    private String note;

    @DBRef
    @NotNull
    @JsonDeserialize(using = OrganisationUnitDeserializer.class)
    private OrganisationUnit organisationUnitID;
    @DBRef
    @NotNull
    @JsonDeserialize(using = EmployeeDeserializer.class)
    private Employee createdBy;

    /*@PersistenceConstructor
    public ReportedOverlimitTransaction(String id, OrderCategory orderCategory, State state, Account sourceAccount,
                                        Client clientId, String identificationId, Money amount, )

*/
}
