package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Fee;
import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.domain.PaymentStatus;
import ge.bog.sst_service.domain.Provider;

import java.util.List;

public interface PaymentService {
    Payment create(Payment payment);
    Payment findById(Long id);
    Payment update(Long id, Payment payment);
    void delete(Long id);
    boolean existsById(Long id);
    List<Payment> findAllByStatus(PaymentStatus status);
    List<Payment> findAllByStatusAndProvider(PaymentStatus status, Provider provider);
    List<Payment> findAll(Long terminalId, Long providerId, String abonentCode);
    void processPayment(Payment payment);
}
