package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider create(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Provider findById(Long id) {
        return providerRepository.findById(id).orElseThrow();
    }

    @Override
    public Provider update(Long id, Provider provider) {
        //provider.setId(id);  // TODO: check if mannual is ok
        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long id) {
        providerRepository.deleteById(id);
    }
}
