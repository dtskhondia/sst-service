package ge.bog.sst_service.domain;

import ge.bog.sst_service.entity.TerminalEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static ge.bog.sst_service.domain.PaymentStatus.CREATED;

@Data
@Entity
@Table(name="payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "terminal_id")
    private TerminalEntity terminal;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @NotBlank
    private String abonentCode;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private LocalDateTime createTime;

    @PrePersist
    public void prePersist(){
        if(status == null) status = CREATED;
        if(createTime == null) createTime = LocalDateTime.now();
    }

    public Boolean isValidAmount(){
        return provider.isAmountInRange(amount);
    }
}
