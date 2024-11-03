package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.Fee;
import ge.bog.sst_service.dto.FeeReadDto;
import ge.bog.sst_service.dto.FeeWriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface FeeMapper {
    @Mapping(source = "providerId", target = "provider.id")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    Fee map(FeeWriteDto feeWriteDto);

    FeeReadDto map(Fee fee);
    List<FeeReadDto> map(List<Fee> feeList);
}
