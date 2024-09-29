package ge.bog.sst_service.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private Long id;

    private Terminal terminal;

    private Provider provider;

    private String abonentCode;

    private BigDecimal amount;

    private PaymentStatus status;

    private LocalDateTime createTime;
}
