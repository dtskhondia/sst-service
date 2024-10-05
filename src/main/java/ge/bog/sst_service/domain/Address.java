package ge.bog.sst_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Embeddable
@Data
public class Address {
    @NotNull
    private String street;

    @NotNull
    private String city;
}
