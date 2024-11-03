package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Fee;
import ge.bog.sst_service.domain.FeeStatus;
import ge.bog.sst_service.domain.Provider;

import java.util.List;

public interface FeeService {
    Fee create(Fee fee);
    Fee findById(Long id);
    Fee update(Long id, Fee fee);
    void delete(Long id);
    boolean existsById(Long id);
    List<Fee> findAllByStatus(FeeStatus status);
    List<Fee> findAll(Long terminalId, Long providerId, String abonentCode);
}
