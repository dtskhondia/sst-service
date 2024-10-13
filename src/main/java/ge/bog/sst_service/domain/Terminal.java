package ge.bog.sst_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @NotNull
    private boolean active;

    @NotNull
    private LocalDateTime lastAccessTime;

    @ManyToMany(fetch = FetchType.EAGER) //TODO: remove eager ?
    @JoinTable(
        joinColumns = {@JoinColumn(name="terminal_id")},
        inverseJoinColumns = {@JoinColumn(name = "provider_id")}
    )
    private List<Provider> availableProviders;
}

