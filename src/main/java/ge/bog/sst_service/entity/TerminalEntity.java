package ge.bog.sst_service.entity;

import ge.bog.sst_service.domain.Address;
import ge.bog.sst_service.domain.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="terminals")
public class TerminalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @NotNull
    private boolean active;

    @NotNull
    private LocalDateTime lastAccessTime;
}

