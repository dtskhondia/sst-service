package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentWriteDto(
    @NotNull Long terminalId,
    @NotNull Long providerId,
    @NotBlank String abonentCode,
    @NotNull BigDecimal amount
){}
