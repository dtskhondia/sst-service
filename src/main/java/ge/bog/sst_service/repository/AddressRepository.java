package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Address;

import java.util.Optional;

public interface AddressRepository {
    Address create(Address address);
    Optional<Address> findById(Long id);
    Address update(Long id, Address address);
    void delete(Long id);
}
