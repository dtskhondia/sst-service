package ge.bog.sst_service.exception;

import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
