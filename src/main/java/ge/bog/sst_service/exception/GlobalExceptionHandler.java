package ge.bog.sst_service.exception;

import org.springdoc.api.ErrorMessage;
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
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {PaymentTerminalNotFoundException.class})
    public ResponseEntity<Object> handlePaymentTerminalNotFoundException(PaymentTerminalNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {PaymentProviderNotFoundException.class})
    public ResponseEntity<Object> handlePaymentProviderNotFoundException(PaymentProviderNotFoundException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage());
        return new ResponseEntity<Object>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
