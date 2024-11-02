package ge.bog.sst_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ge.bog.sst_service.domain.PaymentStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentReadDto(
    Long id,
    TerminalDto terminal,
    ProviderDto provider,
    String abonentCode,
    BigDecimal amount,
    PaymentStatus status,
    LocalDateTime createTime
){}
