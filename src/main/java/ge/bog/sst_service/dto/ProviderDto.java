package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.ProviderGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record ProviderDto(
    @Schema(example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @Schema(example = "MAGTI")
    @NotBlank String name,
    @Schema(example = "MAGTI#DEPT")
    @NotBlank String deptCode,
    @Schema(example = "MAGTI#PAY")
    @NotBlank String payCode,
    @Schema(example = "10")
    @NotNull Integer maxThreads,
    @Schema(example = "1")
    @NotNull BigDecimal minAmount,
    @Schema(example = "100")
    @NotNull BigDecimal maxAmount,
    @NotNull boolean active
){}
