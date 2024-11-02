package ge.bog.sst_service.processor;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.service.PaymentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

import static ge.bog.sst_service.domain.PaymentStatus.CREATED;

@Service
@NoArgsConstructor(force = true) //TODO: is force good practice
@RequiredArgsConstructor
public class ProviderProcessor implements Runnable {
    private final Provider provider;
    private final ExecutorService executorService;
    private final PaymentService paymentService;

    @Override
    public void run() {
        List<Payment> paymentList = paymentService.findAllByStatusAndProvider(CREATED,provider);
        System.out.println("Provider: " + provider.getName() + " Payments: " + paymentList.size());

        for(Payment payment: paymentList){
            PaymentProcessor paymentProcessor = new PaymentProcessor(payment,paymentService);
            executorService.submit(paymentProcessor);
        }
    }
}
