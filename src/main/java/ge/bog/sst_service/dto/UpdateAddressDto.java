package ge.bog.sst_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAddressDto {
    @NotBlank
    private String street;

    @NotBlank
    private String city;
}
