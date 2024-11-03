package ge.bog.sst_service.service;

import ge.bog.sst_service.entity.LogEntity;
import ge.bog.sst_service.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Primary
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    @Override
    public LogEntity logRequest(HttpServletRequest httpServletRequest) {
        LogEntity logEntity = LogEntity.builder()
            .method(httpServletRequest.getMethod())
            .uri(httpServletRequest.getRequestURI())
            .requestBody(httpServletRequest.getParameterMap())
            .requestTime(new Date())
            .build();
        return logRepository.save(logEntity);
    }

    @Override
    public LogEntity logResponse(LogEntity logEntity, HttpServletResponse httpServletResponse, Object result, Exception ex) {
        logEntity.setResponseTime(new Date());

        if(ex != null)
            setError(logEntity, httpServletResponse, ex);
        else
            setInfo(logEntity, httpServletResponse, result);

        return logRepository.save(logEntity);
    }

    private void setInfo(LogEntity logEntity, HttpServletResponse httpServletResponse, Object result){
        logEntity.setLogLevel("SUCCESS");
        logEntity.setResponseBody(result);
    }

    private void setError(LogEntity logEntity, HttpServletResponse httpServletResponse, Exception ex){
        logEntity.setLogLevel("ERROR");
        logEntity.setResponseBody(ex.getMessage());
    }
}
