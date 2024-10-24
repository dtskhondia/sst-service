package ge.bog.sst_service.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity<Object>(errorMessage,HttpStatus.NOT_FOUND);
    }
}
