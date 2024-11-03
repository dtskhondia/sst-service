package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.domain.PaymentStatus;
import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.exception.PaymentProviderNotFoundException;
import ge.bog.sst_service.exception.PaymentTerminalNotFoundException;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

import static ge.bog.sst_service.domain.PaymentStatus.*;

@Service
@Primary
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final TerminalService terminalService;
    private final ProviderService providerService;

    @Override
    public Payment create(Payment payment) {
        if( !terminalService.existsById(payment.getTerminal().getId())) {
            payment.setTerminal(null);
            payment.setStatus(REJECTED);
        }

        if(!providerService.existsById(payment.getProvider().getId())){
            payment.setProvider(null);
            payment.setStatus(REJECTED);
        }

        Payment newPayment = paymentRepository.save(payment);

        if(payment.getTerminal() == null) {
            throw new PaymentTerminalNotFoundException(
                "Payment Rejected. Terminal Not Found"
            );
        }

        if(payment.getProvider().getId() == null) {
            throw new PaymentProviderNotFoundException(
                "Payment Rejected. Provider Not Found"
            );
        }

        return newPayment;
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Payment With ID " + id + " Not Found"));
    }

    @Override
    public Payment update(Long id, Payment payment) {
        payment.setId(id);
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public List<Payment> findAllByStatus(PaymentStatus paymentStatus){
        return paymentRepository.findAllByStatus(paymentStatus);
    }

    @Override
    public List<Payment> findAllByStatusAndProvider(PaymentStatus status, Provider provider) {
        return paymentRepository.findAllByStatusAndProvider(status, provider);
    }

    @Override
    public void processPayment(Payment payment){
        payment.setStatus(PENDING);
        update(payment.getId(), payment);

        if( !payment.isValidAmount()) {
            payment.setStatus(REJECTED);
            update(payment.getId(), payment);
            return;
        }

        try {
            // Simulate Payment Processing
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            payment.setStatus(REJECTED);
            update(payment.getId(), payment);
            return;
        }

        payment.setStatus(PERFORMED);
        update(payment.getId(), payment);
    }
}
