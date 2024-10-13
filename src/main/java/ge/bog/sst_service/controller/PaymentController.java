package ge.bog.sst_service.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import ge.bog.sst_service.dto.PaymentDto;
import ge.bog.sst_service.mapper.PaymentMapper;
import ge.bog.sst_service.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            content = @Content(schema = @Schema(implementation = Empty.class ))
        )
    })
    @PostMapping()
    PaymentDto create(
        @Parameter(description = "Payment To Be Created")
        @RequestBody @Valid PaymentDto paymentDto
    ){
        return paymentMapper.map(paymentService.create(paymentMapper.map(paymentDto)));
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
            content = {}
        )
    })
    @GetMapping("/{id}")
    PaymentDto findById(
        @Parameter(name="id", description = "Payment Id", example = "1")
        @PathVariable Long id
    ){
        return paymentMapper.map(paymentService.findById(id));
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
            content = {}
        )
    })
    @PutMapping("/{id}")
    PaymentDto update(
        @Parameter(name="id", description = "Payment Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Payment To Be Updated")
        @RequestBody @Valid PaymentDto paymentDto
    ){
        return paymentMapper.map(paymentService.update(id, paymentMapper.map(paymentDto)));
    }

    @Operation(summary = "Delete Payment By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Payment Deleted",
            content = {}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Delete Payment",
            content = {}
        )
    })
    @DeleteMapping("/{id}")
    void delete(
        @Parameter(name="id", description = "Payment Id", example = "1")
        @PathVariable Long id
    ){
        paymentService.delete(id);
    }
}
