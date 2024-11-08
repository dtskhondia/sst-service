package ge.bog.sst_service.service;


import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.entity.TerminalEntity;

import java.time.LocalDateTime;

public interface TerminalService {
    Terminal create(Terminal terminal);
    TerminalEntity findEntityById(Long id);
    Terminal findById(Long id);
    Terminal update(Long id, Terminal terminal);
    void delete(Long id);
    boolean existsById(Long id);
    void access(Long id, LocalDateTime accessTime);
}
