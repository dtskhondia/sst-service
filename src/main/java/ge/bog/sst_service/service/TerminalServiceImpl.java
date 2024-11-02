package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.entity.TerminalEntity;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.mapper.TerminalMapper;
import ge.bog.sst_service.repository.TerminalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class TerminalServiceImpl implements TerminalService {
    private final TerminalRepository terminalRepository;
    private final TerminalMapper terminalMapper;

    @Override
    public Terminal create(Terminal terminal) {
        TerminalEntity terminalEntity = terminalMapper.mapToEntity(terminal);
        return terminalMapper.mapToDomain(terminalRepository.save(terminalEntity));
    }

    @Override
    public Terminal findById(Long id) {
        TerminalEntity terminalEntity = terminalRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Terminal With Id " + id + " Not Found")
        );
        return terminalMapper.mapToDomain(terminalEntity);
    }

    @Override
    public Terminal update(Long id, Terminal terminal) {
        terminal.setId(id); // TODO: better version ?
        TerminalEntity terminalEntity = terminalMapper.mapToEntity(terminal);
        return terminalMapper.mapToDomain(terminalRepository.save(terminalEntity));
    }

    @Override
    public void delete(Long id) {
        terminalRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return terminalRepository.existsById(id);
    }
}
