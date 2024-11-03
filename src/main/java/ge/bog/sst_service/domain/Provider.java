package ge.bog.sst_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String deptCode;

    private String payCode;

    private Integer maxThreads;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private boolean active;

    @ManyToOne()
    @JoinColumn(name = "provider_group_id")
    private ProviderGroup providerGroup;

    public Boolean isAmountInRange(BigDecimal amount){
        return minAmount.compareTo(amount) <= 0 && maxAmount.compareTo(amount) >= 0;
    }
}

