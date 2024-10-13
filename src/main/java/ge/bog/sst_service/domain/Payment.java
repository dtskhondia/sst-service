package ge.bog.sst_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//TODO: property validations must be here or only in DTO ?
@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @NotBlank
    private String abonentCode;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PaymentStatus status;

    @NotNull
    private LocalDateTime createTime;
}
