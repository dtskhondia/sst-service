package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;

    @Override
    public Terminal create(Terminal terminal) {
        return terminalRepository.save(terminal);
    }

    @Override
    public Terminal findById(Long id) {
        Terminal terminal = terminalRepository.getById(id);
        if(terminal == null)
            throw new ResourceNotFoundException("Terminal With Id " + id + " Not Found");
        return terminal;
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
