package ge.bog.sst_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddressDto(
    @NotBlank String street,
    @NotBlank String city
){}
