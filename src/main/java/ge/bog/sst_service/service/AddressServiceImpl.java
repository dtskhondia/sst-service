package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Address;
import ge.bog.sst_service.repository.AddressRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepositoryJpa addressRepository;

    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address update(Long id, Address address) {
        address.setId(id); // TODO: check if mannual is ok
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
