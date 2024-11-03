package ge.bog.sst_service.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import ge.bog.sst_service.dto.ProviderDto;
import ge.bog.sst_service.exception.ExceptionResponse;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.mapper.ProviderMapper;
import ge.bog.sst_service.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderMapper providerMapper;
    private final ProviderService providerService;

    @Operation(summary = "Create Provider")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Created"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Create Provider",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PostMapping()
    ProviderDto create(
        @Parameter(description = "Provider Object to Be Created")
        @RequestBody @Valid ProviderDto providerDto
    ){
        return providerMapper.map(providerService.create(providerMapper.map(providerDto)));
    }

    @Operation(summary = "Find Provider By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Found"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Provider Not Found",
            content = {@Content(schema = @Schema(implementation = ResourceNotFoundException.class))}
        )
    })
    @GetMapping("/{id}")
    ProviderDto find(
        @Parameter(name = "id", description = "Provider Id", example = "1")
        @PathVariable Long id
    ){
        ProviderDto providerDto = providerMapper.map(providerService.findById(id));
        if(providerDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider Not Found");
        }
        return providerDto;
    }

    @Operation(summary = "Update Provider By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Updated"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Update Provider",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PutMapping("/{id}")
    ProviderDto update(
        @Parameter(name = "id", description = "Provider Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Provider Object to Be Updated")
        @RequestBody @Valid ProviderDto providerDto
    ){
        return providerMapper.map(providerService.update(id, providerMapper.map(providerDto) ) );
    }

    @Operation(summary = "Delete Provider By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Deleted",
            content = @Content(schema = @Schema(implementation = Empty.class ))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Delete Provider",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
        @Parameter(name = "id", description = "Provider Id", example = "1")
        @PathVariable Long id
    ){
        providerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
