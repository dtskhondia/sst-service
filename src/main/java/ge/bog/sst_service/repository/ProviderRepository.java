package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Provider;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
