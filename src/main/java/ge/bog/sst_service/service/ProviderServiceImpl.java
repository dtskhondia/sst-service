package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

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
        provider.setId(id);  // TODO: check if mannual is ok
        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long id) {
        providerRepository.deleteById(id);
    }

    @Override
    public List<Provider> findAllByActive(Boolean active) {
        return providerRepository.findAllByActive(active);
    }

    @Override
    public List<Provider> findAllByIdIn(List<Long> ids) {
        return providerRepository.findAllByIdIn(ids);
    }

}
