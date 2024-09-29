package ge.bog.sst_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProviderDto {
    @NotBlank
    private String name;

    @NotBlank
    private String deptCode;

    @NotBlank
    private String payCode;

    @NotNull
    private Integer maxThreads;

    @NotNull
    private BigDecimal minAmount;

    @NotNull
    private BigDecimal maxAmount;

    @NotNull
    private boolean active;
}
