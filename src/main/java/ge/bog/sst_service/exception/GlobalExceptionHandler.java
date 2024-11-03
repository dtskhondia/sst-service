package ge.bog.sst_service.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .errorCode(DataIntegrityViolationException.class.getSimpleName())
            .errorMessage(ex.getMessage()).
            build();

        return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .errorCode(ResourceNotFoundException.class.getSimpleName())
            .errorMessage(ex.getMessage()).
            build();

        return new ResponseEntity<Object>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PaymentTerminalNotFoundException.class})
    public ResponseEntity<Object> handlePaymentTerminalNotFoundException(PaymentTerminalNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .errorCode(PaymentTerminalNotFoundException.class.getSimpleName())
            .errorMessage(ex.getMessage()).
            build();

        return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PaymentProviderNotFoundException.class})
    public ResponseEntity<Object> handlePaymentProviderNotFoundException(PaymentProviderNotFoundException ex, WebRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
            .errorCode(PaymentProviderNotFoundException.class.getSimpleName())
            .errorMessage(ex.getMessage()).
            build();

        return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
