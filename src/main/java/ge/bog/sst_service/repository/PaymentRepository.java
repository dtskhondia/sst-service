package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
