package ge.bog.sst_service.mapper;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PaymentMapper {
    @Mapping(source = "providerId", target = "provider.id")
    @Mapping(source = "terminalId", target = "terminal.id")
    Payment map(PaymentDto paymentDto);

    @Mapping(source = "provider.id", target = "providerId")
    @Mapping(source = "terminal.id", target = "terminalId")
    PaymentDto map(Payment payment);
}
