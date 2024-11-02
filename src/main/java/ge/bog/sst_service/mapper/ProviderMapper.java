package ge.bog.sst_service.mapper;


import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.dto.ProviderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = ProviderGroupMapper.class)
public interface ProviderMapper {
    @Mapping(target = "providerGroup", ignore = true)
    Provider map(ProviderDto providerDto);

    ProviderDto map(Provider provider);

    @Named("providerToId")
    static Long providerToId(Provider provider){
        return  provider.getId();
    }

    @Named("idToProvider")
    static Provider idToProvider(Long id){
        Provider provider = new Provider();
        provider.setId(id);
        return provider;
    }
}
