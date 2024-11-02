package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.PaymentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull Long terminalId,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    TerminalDto terminal,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull Long providerId,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    ProviderDto provider,
    @NotBlank String abonentCode,
    @NotNull BigDecimal amount,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) PaymentStatus status,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) LocalDateTime createTime
){}
