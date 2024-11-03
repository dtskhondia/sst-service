package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.entity.TerminalEntity;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.mapper.TerminalMapper;
import ge.bog.sst_service.repository.TerminalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Primary
@RequiredArgsConstructor
public class TerminalServiceImpl implements TerminalService {
    private final TerminalRepository terminalRepository;
    private final TerminalMapper terminalMapper;
    private final ProviderService providerService;

    @Override
    public Terminal create(Terminal terminal) {
        TerminalEntity terminalEntity = terminalMapper.mapToEntity(terminal);
        Terminal newTerminal = terminalMapper.mapToDomain(terminalRepository.save(terminalEntity));
        setAvailableProviders(newTerminal);
        return newTerminal;
    }

    @Override
    public Terminal findById(Long id) {
        TerminalEntity terminalEntity = terminalRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Terminal With Id " + id + " Not Found")
        );
        Terminal terminal = terminalMapper.mapToDomain(terminalEntity);
        setAvailableProviders(terminal);

        return terminal;
    }

    @Override
    public Terminal update(Long id, Terminal terminal) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Terminal With Id " + id + " Not Found");
        };

        terminal.setId(id);
        TerminalEntity terminalEntity = terminalMapper.mapToEntity(terminal);
        Terminal updatedTerminal = terminalMapper.mapToDomain(terminalRepository.save(terminalEntity));
        setAvailableProviders(updatedTerminal);
        return updatedTerminal;
    }

    @Override
    public void delete(Long id) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Terminal With Id " + id + " Not Found");
        };

        terminalRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return terminalRepository.existsById(id);
    }

    @Override
    public void access(Long id, LocalDateTime accessTime){
        Terminal terminal = findById(id);
        terminal.setLastAccessTime(accessTime);
        update(id, terminal);
    }

    private void setAvailableProviders(Terminal terminal){
        terminal.setAvailableProviders(providerService.findByActiveTrue());
    }
}
