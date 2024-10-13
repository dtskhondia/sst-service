package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.Address;
import ge.bog.sst_service.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    AddressDto map(Address address);
    Address map(AddressDto addressDto);
}
