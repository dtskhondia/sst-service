package ge.bog.sst_service.exception;

public class PaymentTerminalNotFoundException extends RuntimeException{
    public PaymentTerminalNotFoundException(String message){
        super(message);
    }
}
