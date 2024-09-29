package ge.bog.sst_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}

