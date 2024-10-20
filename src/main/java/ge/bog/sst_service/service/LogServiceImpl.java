package ge.bog.sst_service.service;

import ge.bog.sst_service.entity.Log;
import ge.bog.sst_service.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

@Service
@Primary
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    @Override
    public Log logRequest(HttpServletRequest httpServletRequest) {
        Log log = new Log();
        log.setMethod(httpServletRequest.getMethod());
        log.setUri(httpServletRequest.getRequestURI());
        log.setRequestBody(httpServletRequest.getParameterMap().toString());
        log.setLogTime(new Date());
        return logRepository.save(log);
    }

    @Override
    public Log logResponse(Log log, HttpServletResponse httpServletResponse, Object result) {
        log.setResponseBody(result.toString());
        return logRepository.save(log);
    }

    public Log logError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        Log log = new Log();
        log.setMethod(request.getMethod());
        log.setUri(request.getRequestURI());
        log.setRequestBody(request.getParameterMap().toString());
        log.setResponseBody("Error: " + e.getMessage());
        log.setLogTime(new Date());

        return logRepository.save(log);
    }
}
