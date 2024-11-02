package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record ProviderGroupDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @NotBlank String name,
    @NotBlank String description,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Long> providerIds,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ProviderDto> providers
){}
