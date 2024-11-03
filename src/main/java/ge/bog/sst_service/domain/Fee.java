package ge.bog.sst_service.domain;

import ge.bog.sst_service.entity.TerminalEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="fees")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @NotBlank
    private String abonentCode;

    @NotNull
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private FeeStatus status;

    private LocalDateTime createTime;
}
