package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.Payment;

public interface PaymentService {
    Payment create(Payment payment);
    Payment findById(Long id);
    Payment update(Long id, Payment payment);
    void delete(Long id);
}
