package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TerminalDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    AddressDto address,
    @NotNull boolean active,
    @NotNull LocalDateTime lastAccessTime,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ProviderDto> availableProviders
){}
