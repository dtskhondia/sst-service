package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.repository.ProviderRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepositoryJpa providerRepository;

    @Override
    public Provider create(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public Optional<Provider> findById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    public Provider update(Long id, Provider provider) {
        provider.setId(id); // TODO: check if mannual is ok
        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long id) {
        providerRepository.deleteById(id);
    }
}
