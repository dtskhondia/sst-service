package ge.bog.sst_service.job;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.processor.ProviderProcessor;
import ge.bog.sst_service.service.PaymentService;
import ge.bog.sst_service.service.ProviderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ProviderJob {
    private final PaymentService paymentService;
    private final ProviderService providerService;

    @PostConstruct
    private void postConstruct(){
        List<Provider> providerList = providerService.findByActiveTrue();
        for(Provider provider: providerList){
            scheduleProvider(provider);
        }
    }

    private void scheduleProvider(Provider provider){
        if(provider.getMaxThreads() == 0) return;

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(provider.getMaxThreads());

        ProviderProcessor providerProcessor = ProviderProcessor.builder()
            .provider(provider)
            .executorService(executorService)
            .paymentService(paymentService)
            .build();

        scheduledExecutorService.scheduleAtFixedRate(
            providerProcessor,
            1,
            30,
            TimeUnit.SECONDS
        );
    }

}
