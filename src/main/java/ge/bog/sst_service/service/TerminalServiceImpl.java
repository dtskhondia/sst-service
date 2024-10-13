package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;

    @Override
    public Terminal create(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    @Override
    public Terminal findById(Long id) {
        return terminalRepository.getById(id);
    }

    @Override
    public Terminal update(Long id, Terminal terminal) {
        terminal.setId(id); // TODO: better version ?
        return terminalRepository.save(terminal);
    }

    @Override
    public void delete(Long id) {
        terminalRepository.deleteById(id);
    }
}
