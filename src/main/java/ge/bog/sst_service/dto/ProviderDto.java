package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.ProviderGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record ProviderDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @NotBlank String name,
    @NotBlank String deptCode,
    @NotBlank String payCode,
    @NotNull Integer maxThreads,
    @NotNull BigDecimal minAmount,
    @NotNull BigDecimal maxAmount,
    @NotNull boolean active
){}
