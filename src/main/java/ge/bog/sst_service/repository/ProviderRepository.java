package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
