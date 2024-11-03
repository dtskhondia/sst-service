package ge.bog.sst_service.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import ge.bog.sst_service.dto.TerminalDto;
import ge.bog.sst_service.exception.ExceptionResponse;
import ge.bog.sst_service.exception.ResourceNotFoundException;
import ge.bog.sst_service.mapper.TerminalMapper;
import ge.bog.sst_service.service.TerminalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/terminals")
public class TerminalController {
    @Autowired
    private TerminalMapper terminalMapper;
    @Autowired
    private TerminalService terminalService;

    @Operation(summary = "Create Terminal")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Terminal Created"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Create Terminal",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PostMapping()
    TerminalDto create(
        @Parameter(description = "Terminal Object to Be Created")
        @RequestBody @Valid TerminalDto terminalDto){
        return terminalMapper.map(terminalService.create(terminalMapper.map(terminalDto)));
    }

    @Operation(summary = "Find Terminal By Id")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Terminal Found"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Terminal Not Found",
            content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
        )
    })
    @GetMapping("/{id}")
    TerminalDto findById(
        @Parameter(name="id", description = "Terminal Id", example = "1")
        @PathVariable Long id
    ){
        return terminalMapper.map(terminalService.findById(id));
    }

    @Operation(summary = "Update Terminal By Id")
    @ApiResponses( value = {
        @ApiResponse(
            responseCode = "200",
            description = "Terminal Updated"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Update Terminal",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @PutMapping("/{id}")
    TerminalDto update(
        @Parameter(name="id", description = "Terminal Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Terminal To Be Updated With")
        @RequestBody @Valid TerminalDto terminalDto
    ){
        return terminalMapper.map(terminalService.update(id, terminalMapper.map(terminalDto)));
    }

    @Operation(summary = "Delete Terminal By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Terminal Deleted",
            content = @Content(schema = @Schema(implementation = Empty.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Delete Terminal",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class ))
        )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(
        @Parameter(name="id", description = "Terminal Id", example = "1")
        @PathVariable Long id
    ){
        terminalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
