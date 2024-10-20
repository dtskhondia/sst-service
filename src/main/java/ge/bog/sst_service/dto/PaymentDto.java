package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.PaymentStatus;
import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.domain.Terminal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentDto(
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
    @NotNull Long terminalId,
    @NotNull Long providerId,
    @NotBlank String abonentCode,
    @NotNull BigDecimal amount,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) PaymentStatus status,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) LocalDateTime createTime
){}
