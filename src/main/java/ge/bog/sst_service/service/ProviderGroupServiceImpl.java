package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.domain.ProviderGroup;
import ge.bog.sst_service.repository.ProviderGroupRepository;
import ge.bog.sst_service.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProviderGroupServiceImpl implements ProviderGroupService {
    @Autowired
    ProviderGroupRepository providerGroupRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public ProviderGroup create(ProviderGroup providerGroup) {
        for(Provider provider : providerGroup.getProviders()){
            provider.setProviderGroup(providerGroup);
        }

        return providerGroupRepository.save(providerGroup);
    }

    @Override
    public ProviderGroup findById(Long id) {
        return providerGroupRepository.getById(id);
    }

    @Override
    public ProviderGroup update(Long id, ProviderGroup providerGroup) {
        //TODO: better version ?
        providerGroup.setId(id);
        return providerGroupRepository.save(providerGroup);
    }

    @Override
    public void delete(Long id) {
        providerGroupRepository.deleteById(id);
    }
}
