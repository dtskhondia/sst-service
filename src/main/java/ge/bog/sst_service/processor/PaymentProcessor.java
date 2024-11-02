package ge.bog.sst_service.processor;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.service.PaymentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ge.bog.sst_service.domain.PaymentStatus.*;

@Service
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PaymentProcessor implements Runnable{
    private final Payment payment;
    private final PaymentService paymentService;

    @Override
    public void run() {
        System.out.println( "Payment: " + payment.getId() + " " + payment.getStatus() + " Thread: " + Thread.currentThread().getName());
        processPayment(payment);
    }

    private void processPayment(Payment payment){
        if(payment.getStatus().equals(CREATED)){
            paymentService.processPayment(payment);
        }
    }
}
