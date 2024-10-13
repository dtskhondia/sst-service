package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
        return paymentRepository.findById(id).orElseThrow();
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
}
