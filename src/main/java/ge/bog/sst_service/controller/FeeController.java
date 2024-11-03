package ge.bog.sst_service.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import ge.bog.sst_service.domain.FeeStatus;
import ge.bog.sst_service.dto.*;
import ge.bog.sst_service.exception.ExceptionResponse;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.mapper.FeeMapper;
import ge.bog.sst_service.service.FeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fees")
public class FeeController {
    private final FeeService feeService;
    private final FeeMapper feeMapper;

    @Operation(summary = "Create Fee")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Fee Created"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Create Fee",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PostMapping()
    ResponseEntity<FeeReadDto> create(
        @Parameter(description = "Fee To Be Created")
        @RequestBody @Valid FeeWriteDto feeWriteDto
    ){
        FeeReadDto newFee = feeMapper.map(feeService.create(feeMapper.map(feeWriteDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(newFee);
    }

    @Operation(summary = "Find Fee By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Fee Found"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Fee Not Found",
            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class ))
        )
    })
    @GetMapping("/{id}")
    FeeReadDto findById(
        @Parameter(name="id", description = "Fee Id", example = "1")
        @PathVariable Long id
    ){
        return feeMapper.map(feeService.findById(id));
    }

    @Operation(summary = "Find Fees")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Fees Found"
        )
    })
    @GetMapping("")
    List<FeeReadDto> findAll(
        @Parameter(name="terminalId", description = "terminal Id", example = "1")
        @RequestParam Long terminalId,
        @Parameter(name="providerId", description = "Provider Id", example = "1")
        @RequestParam Long providerId,
        @Parameter(name="abonentCode", description = "Abonent Code", example = "100000")
        @RequestParam String abonentCode
    ){
        return feeMapper.map(feeService.findAll(terminalId, providerId,abonentCode));
    }

    @Operation(summary = "Update Fee By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Fee Updated"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Fee Update Failed",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PutMapping("/{id}")
    FeeReadDto update(
        @Parameter(name="id", description = "Fee Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Fee To Be Updated")
        @RequestBody @Valid FeeWriteDto feeWriteDto
    ){
        return feeMapper.map(feeService.update(id, feeMapper.map(feeWriteDto)));
    }

    @Operation(summary = "Delete Fee By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Fee Deleted",
            content = @Content(schema = @Schema(implementation = Empty.class ))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Delete Fee",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
        @Parameter(name="id", description = "Fee Id", example = "1")
        @PathVariable Long id
    ){
        feeService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
