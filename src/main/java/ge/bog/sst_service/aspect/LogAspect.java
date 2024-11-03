package ge.bog.sst_service.aspect;

import ge.bog.sst_service.entity.LogEntity;
import ge.bog.sst_service.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {
    private final LogService logService;

    @Pointcut("execution(* ge.bog.sst_service.controller.*Controller.*(..))")
    public void restControllerPointcut() {}

    @Around("restControllerPointcut()")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        LogEntity logEntity = logService.logRequest(request);

        try {
            Object result = joinPoint.proceed();
            logService.logResponse(logEntity, response, result, null);
            return result;
        } catch (Exception ex) {
            logService.logResponse(logEntity, response, null, ex);
            throw ex;
        }
    }
}
