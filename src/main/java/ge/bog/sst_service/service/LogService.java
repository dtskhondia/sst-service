package ge.bog.sst_service.service;

import ge.bog.sst_service.entity.LogEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface LogService {
    LogEntity logRequest(HttpServletRequest httpServletRequest) throws IOException;
    LogEntity logResponse(LogEntity logEntity, HttpServletResponse httpServletResponse, Object result, Exception ex);
}
