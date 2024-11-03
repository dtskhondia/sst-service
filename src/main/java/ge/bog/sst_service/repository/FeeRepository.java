package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Fee;
import ge.bog.sst_service.domain.FeeStatus;
import ge.bog.sst_service.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Long> {
    boolean existsById(Long id);
    List<Fee> findAllByStatus(FeeStatus status);
    List<Fee> findAllByStatusAndProviderIdAndAbonentCode(FeeStatus status, Long providerId, String abonentCode);
}
