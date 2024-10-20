package ge.bog.sst_service.aspect;

import ge.bog.sst_service.entity.Log;
import ge.bog.sst_service.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {
    private final LogService logService;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("restControllerPointcut()")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("log...........");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        Log log = logService.logRequest(request);

        try {
            Object result = joinPoint.proceed();
            logService.logResponse(log, response, result);
            return result;
        } catch (Exception e) {
            logService.logError(request, response, e);
            throw e;
        }
    }

}
