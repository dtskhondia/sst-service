package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.ProviderGroup;
import ge.bog.sst_service.dto.ProviderGroupDto;
import org.mapstruct.Mapper;

@Mapper(uses = ProviderMapper.class)
public interface ProviderGroupMapper {
    ProviderGroup map(ProviderGroupDto providerGroupDto);

    ProviderGroupDto map(ProviderGroup providerGroup);
}
