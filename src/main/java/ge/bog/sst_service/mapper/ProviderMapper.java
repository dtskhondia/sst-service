package ge.bog.sst_service.mapper;


import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.dto.ProviderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ProviderGroupMapper.class)
public interface ProviderMapper {
    @Mapping(target = "providerGroup.id", source = "providerGroupId")
    Provider map(ProviderDto providerDto);

    @Mapping(target = "providerGroupId", source = "providerGroup.id")
    ProviderDto map(Provider provider);

}
