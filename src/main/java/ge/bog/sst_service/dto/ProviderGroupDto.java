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
    List<Long> providerIds
){}
