package ge.bog.sst_service.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import ge.bog.sst_service.dto.FeeReadDto;
import ge.bog.sst_service.dto.PaymentReadDto;
import ge.bog.sst_service.dto.PaymentWriteDto;
import ge.bog.sst_service.exception.ExceptionResponse;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.mapper.PaymentMapper;
import ge.bog.sst_service.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.ErrorHandler;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentMapper paymentMapper;

    @Operation(summary = "Create Payment")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Payment Created"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Create Payment",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PostMapping()
    ResponseEntity<PaymentReadDto> create(
        @Parameter(description = "Payment To Be Created")
        @RequestBody @Valid PaymentWriteDto paymentWriteDto
    ){
        PaymentReadDto newPayment = paymentMapper.map(paymentService.create(paymentMapper.map(paymentWriteDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(newPayment);
    }

    @Operation(summary = "Find Payment By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment Found"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment Not Found",
            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class ))
        )
    })
    @GetMapping("/{id}")
    PaymentReadDto findById(
        @Parameter(name="id", description = "Payment Id", example = "1")
        @PathVariable Long id
    ){
        return paymentMapper.map(paymentService.findById(id));
    }

    @Operation(summary = "Find Payments")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payments Found"
        )
    })
    @GetMapping("")
    List<PaymentReadDto> findAll(
        @Parameter(name="terminalId", description = "terminal Id", example = "1")
        @RequestParam Long terminalId,
        @Parameter(name="providerId", description = "Provider Id", example = "1")
        @RequestParam Long providerId,
        @Parameter(name="abonentCode", description = "Abonent Code", example = "100000")
        @RequestParam String abonentCode
    ){
        return paymentMapper.map(paymentService.findAll(terminalId, providerId,abonentCode));
    }


    @Operation(summary = "Update Payment By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment Updated"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Payment Update Failed",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PutMapping("/{id}")
    PaymentReadDto update(
        @Parameter(name="id", description = "Payment Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Payment To Be Updated")
        @RequestBody @Valid PaymentWriteDto paymentWriteDto
    ){
        return paymentMapper.map(paymentService.update(id, paymentMapper.map(paymentWriteDto)));
    }

    @Operation(summary = "Delete Payment By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Payment Deleted",
            content = @Content(schema = @Schema(implementation = Empty.class ))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Delete Payment",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
        @Parameter(name="id", description = "Payment Id", example = "1")
        @PathVariable Long id
    ){
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
