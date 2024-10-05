package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.ProviderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProviderGroupRepository extends JpaRepository<ProviderGroup,Long> {
    @Query("select p from ProviderGroup p join fetch p.providers")
    ProviderGroup getById(Long id);
}
