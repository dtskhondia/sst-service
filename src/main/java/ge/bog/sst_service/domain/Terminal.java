package ge.bog.sst_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Terminal {
    @NotNull
    private Long id;

    @Embedded
    private Address address;

    @NotNull
    private boolean active;

    @NotNull
    private LocalDateTime lastAccessTime;

    private List<Provider> availableProviders;

}

