package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    List<Provider> findAllByActive(Boolean active);
    List<Provider> findAllByIdIn(List<Long> providers);
}
