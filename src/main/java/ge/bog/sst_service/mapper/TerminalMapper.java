package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.dto.ProviderDto;
import ge.bog.sst_service.dto.TerminalDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {ProviderMapper.class, AddressMapper.class})
public interface TerminalMapper {
    @Mapping(target = "availableProviders", source = "availableProviderIds", qualifiedByName = "idToProvider")
    Terminal map(TerminalDto terminalDto);

    @Mapping(target = "availableProviderIds", source = "availableProviders", qualifiedByName = "providerToId")
    TerminalDto map(Terminal terminal);
}

