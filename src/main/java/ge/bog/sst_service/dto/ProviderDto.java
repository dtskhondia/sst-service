package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record ProviderDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @NotBlank String name,
    @NotBlank String deptCode,
    @NotBlank String payCode,
    @NotBlank Integer maxThreads,
    @NotBlank BigDecimal minAmount,
    @NotBlank BigDecimal maxAmount,
    @NotBlank boolean active,
    @NotBlank Long providerGroupId
){}
