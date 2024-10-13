package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TerminalRepository extends JpaRepository<Terminal, Long> {
    @Query("select t from Terminal t join fetch t.availableProviders")
    Terminal getById(Long id);
}
