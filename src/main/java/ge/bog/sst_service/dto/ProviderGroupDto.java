package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record ProviderGroupDto(
    @Schema(example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @Schema(example = "INTERNET")
    @NotBlank String name,
    @Schema(example = "INTERNET GROUP")
    @NotBlank String description,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(example = "[1, 2]")
    List<Long> providerIds,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ProviderDto> providers
){}
