package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TerminalDto(
    @Schema(example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    AddressDto address,
    @NotNull boolean active,
    @NotNull LocalDateTime lastAccessTime,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ProviderDto> availableProviders
){}
