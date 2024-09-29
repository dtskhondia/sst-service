package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;

import java.util.Optional;

public interface ProviderService {
    Provider create(Provider address);
    Optional<Provider> findById(Long id);
    Provider update(Long id, Provider provider);
    void delete(Long id);
}
