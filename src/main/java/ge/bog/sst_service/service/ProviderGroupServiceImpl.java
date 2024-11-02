package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.domain.ProviderGroup;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.repository.ProviderGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
@Transactional //TODO: how this works add for detached products
public class ProviderGroupServiceImpl implements ProviderGroupService {
    private final ProviderGroupRepository providerGroupRepository;
    private final ProviderService providerService;

    @Override
    public ProviderGroup create(ProviderGroup providerGroup) {
        List<Provider> providerList = providerService.findAllByIdIn(
            providerGroup.getProviders()
                .stream()
                .map(Provider::getId)
                .collect(Collectors.toList())
        );

        for(Provider provider : providerList){
            provider.setProviderGroup(providerGroup);
        }

        providerGroup.setProviders(providerList);

        return providerGroupRepository.save(providerGroup);
    }

    @Override
    public ProviderGroup findById(Long id) {
        ProviderGroup providerGroup = providerGroupRepository.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("Provider Group With Id " + id + " Not Found")
        );

        List<Provider> providers = providerService.findAllByProviderGroupId(providerGroup.getId());
        providerGroup.setProviders(providers);
        return providerGroup;
    }

    @Override
    public ProviderGroup update(Long id, ProviderGroup providerGroup) {
        providerGroup.setId(id); //TODO: better version ?
        return providerGroupRepository.save(providerGroup);
    }

    @Override
    public void delete(Long id) {
        providerGroupRepository.deleteById(id);
    }
}
