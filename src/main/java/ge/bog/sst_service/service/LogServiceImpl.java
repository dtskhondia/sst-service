package ge.bog.sst_service.service;

import ge.bog.sst_service.entity.LogEntity;
import ge.bog.sst_service.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
        LogEntity logEntity = new LogEntity();
        logEntity.setMethod(httpServletRequest.getMethod());
        logEntity.setUri(httpServletRequest.getRequestURI());
        logEntity.setRequestBody(httpServletRequest.getParameterMap().toString());
        logEntity.setLogTime(new Date());
        return logRepository.save(logEntity);
    }

    @Override
    public LogEntity logResponse(LogEntity logEntity, HttpServletResponse httpServletResponse, Object result) {
        logEntity.setResponseBody(result.toString());
        return logRepository.save(logEntity);
    }

    public LogEntity logError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        LogEntity logEntity = new LogEntity();
        logEntity.setMethod(request.getMethod());
        logEntity.setUri(request.getRequestURI());
        logEntity.setRequestBody(request.getParameterMap().toString());
        logEntity.setResponseBody("Error: " + e.getMessage());
        logEntity.setLogTime(new Date());

        return logRepository.save(logEntity);
    }
}
