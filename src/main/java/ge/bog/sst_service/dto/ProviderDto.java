package ge.bog.sst_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProviderDto {
    private Long id;

    private String name;

    private String deptCode;

    private String payCode;

    private Integer maxThreads;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private boolean active;
}
