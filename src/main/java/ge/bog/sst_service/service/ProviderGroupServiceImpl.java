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
public class ProviderGroupServiceImpl implements ProviderGroupService {
    private final ProviderGroupRepository providerGroupRepository;
    private final ProviderService providerService;

    @Override
    @Transactional
    public ProviderGroup create(ProviderGroup providerGroup) {
        List<Provider> existingProviders = providerService.findAllByIdIn(
            providerGroup.getProviders()
                .stream()
                .map(Provider::getId)
                .collect(Collectors.toList())
        );

        validateProviders(providerGroup.getProviders(), existingProviders);

        for(Provider provider : existingProviders){
            provider.setProviderGroup(providerGroup);
        }

        providerGroup.setProviders(existingProviders);

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

    @Transactional
    @Override
    public ProviderGroup update(Long id, ProviderGroup providerGroup) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Provider Group With Id " + id + " Not Found");
        };

        providerGroup.setId(id);

        List<Provider> existingProviders = providerService.findAllByIdIn(
            providerGroup.getProviders()
                .stream()
                .map(Provider::getId)
                .collect(Collectors.toList())
        );

        validateProviders(providerGroup.getProviders(), existingProviders);

        ProviderGroup currentProviderGroup = findById(id);

        for(Provider provider : currentProviderGroup.getProviders()){
            provider.setProviderGroup(null);
        }

        for(Provider provider : existingProviders){
            provider.setProviderGroup(providerGroup);
        }

        providerGroup.setProviders(existingProviders);

        return providerGroupRepository.save(providerGroup);
    }

    @Override
    public void delete(Long id) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Provider Group With Id " + id + " Not Found");
        };

        providerGroupRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return providerGroupRepository.existsById(id);
    }

    private void validateProviders(List<Provider> newProviders, List<Provider> existingProviders){
        List<Long> existingProviderIds = existingProviders.stream().map(Provider::getId).toList();

        List<Long> missingProviders = newProviders.stream()
            .map(Provider::getId)
            .filter(providerId -> !existingProviderIds.contains(providerId))
            .toList();

        if(!missingProviders.isEmpty()){
            throw new ResourceNotFoundException("Providers Not Found " + missingProviders.toString());
        }
    }
}
