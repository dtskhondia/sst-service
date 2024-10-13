package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.Address;
import ge.bog.sst_service.domain.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    List<Long> availableProviderIds
) {
}
