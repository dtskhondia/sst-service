package ge.bog.sst_service.service;

import ge.bog.sst_service.entity.LogEntity;
import ge.bog.sst_service.repository.LogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    @Override
    public LogEntity logRequest(HttpServletRequest httpServletRequest) throws IOException {
        LogEntity logEntity = LogEntity.builder()
            .method(httpServletRequest.getMethod())
            .uri(httpServletRequest.getRequestURI())
            .requestBody(getRequestBody(httpServletRequest))
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

    public String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();    }
}
