package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Terminal;

public interface TerminalService {
    Terminal create(Terminal terminal);
    Terminal findById(Long id);
    Terminal update(Long id, Terminal terminal);
    void delete(Long id);
}
