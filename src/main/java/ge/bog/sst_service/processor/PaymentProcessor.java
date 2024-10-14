package ge.bog.sst_service.processor;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.domain.PaymentStatus;
import ge.bog.sst_service.service.PaymentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ge.bog.sst_service.domain.PaymentStatus.*;

@Service
@RequiredArgsConstructor
@NoArgsConstructor(force = true) //TODO: is force good practice
public class PaymentProcessor implements Runnable{
    private final Payment payment;
    private final PaymentService paymentService;

    @Override
    public void run() {
        System.out.println( "Payment: " + payment.getId() + " " + payment.getStatus() + " Thread: " + Thread.currentThread().getName());
        processPayment(payment);
    }

    // TODO: handle same payment not to be processed 2 times
    private void processPayment(Payment payment){
        if(payment.getStatus().equals(CREATED)){
            payment.setStatus(PENDING);
            paymentService.update(payment.getId(),payment);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                payment.setStatus(REJECTED);
                paymentService.update(payment.getId(), payment);
            }
            payment.setStatus(PERFORMED);
            paymentService.update(payment.getId(),payment);
        } else{
            payment.setStatus(REJECTED);
            paymentService.update(payment.getId(), payment);
        }
    }
}
