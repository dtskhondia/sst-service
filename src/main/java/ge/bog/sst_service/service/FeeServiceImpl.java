package ge.bog.sst_service.service;

import ge.bog.sst_service.domain.*;
import ge.bog.sst_service.exception.PaymentProviderNotFoundException;
import ge.bog.sst_service.exception.PaymentTerminalNotFoundException;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.repository.FeeRepository;
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
public class FeeServiceImpl implements FeeService {
    private final FeeRepository feeRepository;
    private final ProviderService providerService;
    private final TerminalService terminalService;

    @Transactional
    @Override
    public Fee create(Fee fee) {
        if(!providerService.existsById(fee.getProvider().getId())){
            fee.setProvider(null);
            fee.setStatus(FeeStatus.REJECTED);
        }

        fee.setStatus(FeeStatus.CREATED);
        fee.setCreateTime(LocalDateTime.now());

        Fee newFee = feeRepository.save(fee);

        if(fee.getProvider() == null) {
            throw new PaymentProviderNotFoundException(
                "Fee Rejected. Provider Not Found"
            );
        }

        newFee.setProvider(providerService.findById(fee.getProvider().getId()));

        return newFee;
    }

    @Override
    public Fee findById(Long id) {
        return feeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Fee With ID " + id + " Not Found"));
    }

    @Override
    public Fee update(Long id, Fee fee) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Fee With ID " + id + " Not Found");
        };

        fee.setId(id);
        return feeRepository.save(fee);
    }

    @Override
    public void delete(Long id) {
        if(!existsById(id)) {
            throw new ResourceNotFoundException("Fee With ID " + id + " Not Found");
        };

        feeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id){
        return feeRepository.existsById(id);
    }

    @Override
    public List<Fee> findAllByStatus(FeeStatus feeStatus){
        return feeRepository.findAllByStatus(feeStatus);
    }

    @Override
    public List<Fee> findAll(Long terminalId, Long providerId, String abonentCode) {
        // validate terminal
        Terminal terminal = terminalService.findById(terminalId);
        return feeRepository.findAllByStatusAndProviderIdAndAbonentCode(FeeStatus.CREATED, providerId, abonentCode);
    }
}
