package ge.bog.sst_service.repository;

import ge.bog.sst_service.entity.TerminalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<TerminalEntity, Long> {
    boolean existsById(Long id);
}
