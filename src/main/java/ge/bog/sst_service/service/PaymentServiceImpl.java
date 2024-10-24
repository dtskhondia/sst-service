package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.domain.PaymentStatus;
import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
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
}
