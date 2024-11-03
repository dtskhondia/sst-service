package ge.bog.sst_service.repository;

import ge.bog.sst_service.domain.Payment;
import ge.bog.sst_service.domain.PaymentStatus;
import ge.bog.sst_service.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsById(Long id);
    List<Payment> findAllByStatus(PaymentStatus status);
    List<Payment> findAllByStatusAndProvider(PaymentStatus status, Provider provider);
    List<Payment> findAllByStatusAndTerminalIdAndProviderIdAndAbonentCode(PaymentStatus status, Long terminalId, Long providerId, String abonentCode);
}
