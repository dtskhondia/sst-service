package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
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
        return providerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Provider With Id " + id + " Not Found"));
    }

    @Override
    public Provider update(Long id, Provider provider) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Provider With Id " + id + " Not Found");
        };

        provider.setId(id);
        return providerRepository.save(provider);
    }

    @Override
    public void delete(Long id) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Provider With Id " + id + " Not Found");
        };

        providerRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return providerRepository.existsById(id);
    }

    @Override
    public List<Provider> findByActiveTrue() {
        return providerRepository.findByActiveTrue();
    }

    @Override
    public List<Provider> findAllByIdIn(List<Long> ids) {
        return providerRepository.findAllByIdIn(ids);
    }

    @Override
    public List<Provider> findAllByProviderGroupId(Long providerGroupId) {
        return providerRepository.findAllByProviderGroupId(providerGroupId);
    }

}
