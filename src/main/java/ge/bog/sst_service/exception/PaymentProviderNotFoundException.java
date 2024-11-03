package ge.bog.sst_service.exception;

public class PaymentProviderNotFoundException extends RuntimeException{
    public PaymentProviderNotFoundException(String message){
        super(message);
    }
}
