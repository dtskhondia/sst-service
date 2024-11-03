package ge.bog.sst_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record FeeWriteDto(
    @Schema(example = "1")
    @NotNull Long providerId,
    @Schema(example = "100000")
    @NotBlank String abonentCode,
    @Schema(example = "50")
    @NotNull BigDecimal amount
){}
