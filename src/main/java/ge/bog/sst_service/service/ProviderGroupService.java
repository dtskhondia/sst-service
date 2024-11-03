package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.ProviderGroup;
import org.springframework.stereotype.Service;

@Service
public interface ProviderGroupService {
    ProviderGroup create(ProviderGroup providerGroup);
    ProviderGroup findById(Long id);
    ProviderGroup update(Long id, ProviderGroup providerGroup);
    void delete(Long id);
    boolean existsById(Long id);
}
