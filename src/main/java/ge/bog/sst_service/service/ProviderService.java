package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Provider;

import java.util.List;

public interface ProviderService {
    Provider create(Provider address);
    Provider findById(Long id);
    Provider update(Long id, Provider provider);
    void delete(Long id);
    List<Provider> findAllByActive(Boolean active);
    List<Provider> findAllByIdIn(List<Long>ids);
    List<Provider> findAllByProviderGroupId(Long providerGroupId);
}
