package ge.bog.sst_service.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(
    String errorCode,
    String errorMessage
){}
