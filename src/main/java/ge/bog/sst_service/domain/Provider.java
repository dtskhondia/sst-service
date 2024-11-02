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

    private String deptCode; //TODO: how to use

    private String payCode; //TODO: how to use

    private Integer maxThreads;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER) //TODO: should this be eager ?
    @JoinColumn(name = "provider_group_id")
    private ProviderGroup providerGroup;

    public Boolean isAmountInRange(BigDecimal amount){
        return minAmount.compareTo(amount) <= 0 && maxAmount.compareTo(amount) >= 0;
    }
}

