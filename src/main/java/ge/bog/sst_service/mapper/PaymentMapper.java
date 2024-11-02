package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.dto.PaymentReadDto;
import ge.bog.sst_service.dto.PaymentWriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaymentMapper {
    @Mapping(source = "providerId", target = "provider.id")
    @Mapping(source = "terminalId", target = "terminal.id")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    Payment map(PaymentWriteDto paymentWriteDto);

    PaymentReadDto map(Payment payment);
}
