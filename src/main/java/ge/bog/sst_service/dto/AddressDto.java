package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddressDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @NotBlank String street,
    @NotBlank String city
){}
