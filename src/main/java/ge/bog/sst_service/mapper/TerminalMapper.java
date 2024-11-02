package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.dto.TerminalDto;
import ge.bog.sst_service.entity.TerminalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProviderMapper.class, AddressMapper.class})
public interface TerminalMapper {
    @Mapping(target = "availableProviders", ignore = true)
    Terminal map(TerminalDto terminalDto);

    TerminalDto map(Terminal terminal);

    TerminalEntity mapToEntity(Terminal terminal);
    @Mapping(target = "availableProviders", ignore = true)
    Terminal mapToDomain(TerminalEntity terminalEntity);

}

