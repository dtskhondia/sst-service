package ge.bog.sst_service.dto;

import ge.bog.sst_service.domain.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record FeeReadDto(
    @Schema(example = "1")
    Long id,
    ProviderDto provider,
    @Schema(example = "100000")
    String abonentCode,
    @Schema(example = "50")
    BigDecimal amount,
    PaymentStatus status,
    LocalDateTime createTime
){}
