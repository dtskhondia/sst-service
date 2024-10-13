package ge.bog.sst_service.config;

import ge.bog.sst_service.mapper.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ProviderMapper providerMapper() {
        return Mappers.getMapper( ProviderMapper.class);
    }

    @Bean
    public ProviderGroupMapper providerGroupMapper() {
        return Mappers.getMapper( ProviderGroupMapper.class);
    }

    @Bean
    public TerminalMapper terminalMapper() { return Mappers.getMapper(TerminalMapper.class); }

    @Bean
    public AddressMapper addressMapper() { return Mappers.getMapper(AddressMapper.class); }

    @Bean
    public PaymentMapper paymentMapper() { return Mappers.getMapper(PaymentMapper.class); }
}
