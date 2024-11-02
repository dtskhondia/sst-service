package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.ProviderGroup;
import ge.bog.sst_service.dto.ProviderGroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ProviderMapper.class)
public interface ProviderGroupMapper {
    @Mapping(target = "providers", source = "providerIds", qualifiedByName = "idToProvider")
    ProviderGroup map(ProviderGroupDto providerGroupDto);

    @Mapping(target = "providerIds", ignore = true)
    ProviderGroupDto map(ProviderGroup providerGroup);
}
