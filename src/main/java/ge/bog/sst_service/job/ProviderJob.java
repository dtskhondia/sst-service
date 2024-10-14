package ge.bog.sst_service.job;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.processor.ProviderProcessor;
import ge.bog.sst_service.service.PaymentService;
import ge.bog.sst_service.service.ProviderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ProviderJob {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ProviderService providerService;

    @PostConstruct
    private void postConstruct(){
        List<Provider> providerList = providerService.findAllByActive(true);
        for(Provider provider: providerList){
            scheduleProvider(provider);
        }
    }

    private void scheduleProvider(Provider provider){
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(provider.getMaxThreads());
        //TODO: inject paymentService in Constructor
        ProviderProcessor providerProcessor = new ProviderProcessor(provider, executorService, paymentService);

        scheduledExecutorService.scheduleAtFixedRate(
            providerProcessor,
            1,
            5,
            TimeUnit.SECONDS
        );
    }

}
