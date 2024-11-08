package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentReadDto(
    @Schema(example = "1")
    Long id,
    TerminalDto terminal,
    ProviderDto provider,
    @Schema(example = "100000")
    String abonentCode,
    @Schema(example = "50")
    BigDecimal amount,
    PaymentStatus status,
    LocalDateTime createTime
){}
