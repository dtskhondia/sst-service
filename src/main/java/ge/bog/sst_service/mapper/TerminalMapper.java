package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.dto.TerminalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProviderMapper.class, AddressMapper.class})
public interface TerminalMapper {
    @Mapping(target = "availableProviders", source = "availableProviderIds", qualifiedByName = "idToProvider")
    Terminal map(TerminalDto terminalDto);

    @Mapping(target = "availableProviderIds", source = "availableProviders", qualifiedByName = "providerToId")
    TerminalDto map(Terminal terminal);
}

