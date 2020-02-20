package stuba.fei.gono.javablocking.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import stuba.fei.gono.javablocking.data.ClientDeserializer;
import stuba.fei.gono.javablocking.data.ZonedDateTimeDeserializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@Document(collection = "clients")
@TypeAlias(value = "client")
public class Client {
    @NotBlank
    private String firstName;
    @NotBlank
    private String surName;
    @NotBlank

    //private ZonedDateTime birthDate;
    @Id
    private String id;
}
