package ge.bog.sst_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddressDto(
    @Schema(example = "Silicon Street")
    @NotBlank String street,
    @Schema(example = "Silicon Valley")
    @NotBlank String city
){}
