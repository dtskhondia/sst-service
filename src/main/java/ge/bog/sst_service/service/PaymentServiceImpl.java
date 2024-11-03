package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.*;
import ge.bog.sst_service.exception.PaymentProviderNotFoundException;
import ge.bog.sst_service.exception.PaymentTerminalNotFoundException;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.repository.PaymentRepository;
import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static ge.bog.sst_service.domain.PaymentStatus.*;

@Service
@Primary
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final TerminalService terminalService;
    private final ProviderService providerService;

    @Transactional
    @Override
    public Payment create(Payment payment) {
        LocalDateTime paymentTime = LocalDateTime.now();

        if( !terminalService.existsById(payment.getTerminal().getId())) {
            payment.setTerminal(null);
            payment.setStatus(REJECTED);
        } else {
            terminalService.access(payment.getTerminal().getId(), paymentTime);
        }

        if(!providerService.existsById(payment.getProvider().getId())){
            payment.setProvider(null);
            payment.setStatus(REJECTED);
        }

        payment.setStatus(CREATED);
        payment.setCreateTime(paymentTime);

        Payment newPayment = paymentRepository.save(payment);

        if(payment.getTerminal() == null) {
            throw new PaymentTerminalNotFoundException(
                "Payment Rejected. Terminal Not Found"
            );
        }

        if(payment.getProvider() == null) {
            throw new PaymentProviderNotFoundException(
                "Payment Rejected. Provider Not Found"
            );
        }

        newPayment.setProvider(providerService.findById(payment.getProvider().getId()));
        newPayment.setTerminal(terminalService.findEntityById(payment.getTerminal().getId()));

        return newPayment;
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Payment With ID " + id + " Not Found"));
    }

    @Override
    public Payment update(Long id, Payment payment) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Payment With ID " + id + " Not Found");
        };

        payment.setId(id);
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Payment With ID " + id + " Not Found");
        };

        paymentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return paymentRepository.existsById(id);
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
    public List<Payment> findAll(Long terminalId, Long providerId, String abonentCode) {
        return paymentRepository.findAllByStatusAndTerminalIdAndProviderIdAndAbonentCode(PERFORMED, terminalId, providerId, abonentCode);
    }

    @Transactional
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
