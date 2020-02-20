package stuba.fei.gono.javablocking.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import stuba.fei.gono.javablocking.data.*;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonSerialize(using = ClientSerializer.class)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss a z")
    private ZonedDateTime modificationDate;


    //@Past(message = "INVALID_DATE_IN_PAST")
    //@BankingDay(message = "INVALID_DATE")
    @NotNull
    @FutureOrPresent(message = "INVALID_DATE_IN_PAST")
    @DaysBeforeDate(message = "FIELD_INVALID_TOO_NEAR_IN_FUTURE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date transferDate;

    private String note;

    @DBRef
    @NotNull
    @JsonDeserialize(using = OrganisationUnitDeserializer.class)
    @JsonSerialize(using = OrganisationUnitSerializer.class)
    private OrganisationUnit organisationUnitID;
    @DBRef
    @NotNull
    @JsonDeserialize(using = EmployeeDeserializer.class)
    @JsonSerialize(using = EmployeeSerializer.class)
    private Employee createdBy;

    /*@PersistenceConstructor
    public ReportedOverlimitTransaction(String id, OrderCategory orderCategory, State state, Account sourceAccount,
                                        Client clientId, String identificationId, Money amount, )

*/
}
