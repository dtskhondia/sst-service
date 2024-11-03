package ge.bog.sst_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="provider_groups")
public class ProviderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @OneToMany(mappedBy = "providerGroup", fetch = FetchType.EAGER)
    private List<Provider> providers;
}

