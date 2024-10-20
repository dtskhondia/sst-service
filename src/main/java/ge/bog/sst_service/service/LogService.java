package ge.bog.sst_service.service;

import ge.bog.sst_service.entity.Log;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LogService {
    Log logRequest(HttpServletRequest httpServletRequest);
    Log logResponse(Log log, HttpServletResponse httpServletResponse, Object result);
    Log logError(HttpServletRequest request, HttpServletResponse response, Exception e);
}
