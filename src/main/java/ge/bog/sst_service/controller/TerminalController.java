package ge.bog.sst_service.controller;

import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.domain.Terminal;
import ge.bog.sst_service.dto.TerminalDto;
import ge.bog.sst_service.mapper.AddressMapper;
import ge.bog.sst_service.mapper.TerminalMapper;
import ge.bog.sst_service.service.ProviderService;
import ge.bog.sst_service.service.TerminalService;
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
            description = "Terminal Created",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TerminalDto.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Create Terminal"
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
            description = "Terminal Found",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = TerminalDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Terminal Not Found"
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
            description = "Terminal Updated",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = TerminalDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Update Terminal"
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
            description = "Terminal Deleted"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed To Delete Terminal"
        )
    })
    @DeleteMapping("/{id}")
    void delete(
        @Parameter(name="id", description = "Terminal Id", example = "1")
        @PathVariable Long id
    ){
        terminalService.delete(id);
    }
}
