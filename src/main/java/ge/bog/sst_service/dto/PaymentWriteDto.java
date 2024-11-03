package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentWriteDto(
    @Schema(example = "1")
    @NotNull Long terminalId,
    @Schema(example = "1")
    @NotNull Long providerId,
    @Schema(example = "100000")
    @NotBlank String abonentCode,
    @Schema(example = "50")
    @NotNull BigDecimal amount,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    PaymentStatus status,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime createTime
){}
