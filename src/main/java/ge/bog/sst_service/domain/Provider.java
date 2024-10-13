package ge.bog.sst_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
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

    @ManyToOne(fetch = FetchType.EAGER) //TODO: should this be eager
    @JoinColumn(name = "provider_group_id")
    private ProviderGroup providerGroup;
}

