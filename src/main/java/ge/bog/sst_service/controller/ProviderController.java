package ge.bog.sst_service.controller;

import ge.bog.sst_service.domain.Address;
import ge.bog.sst_service.domain.Provider;
import ge.bog.sst_service.dto.*;
import ge.bog.sst_service.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProviderService providerService;

    @Operation(summary = "Create Provider")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Created",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProviderDto.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Create Provider",
            content = { @Content( mediaType = "application/json") }
        )
    })
    @PostMapping()
    ProviderDto create(
        @Parameter(description = "Provider Object to Be Created")
        @RequestBody @Valid CreateProviderDto createProviderDto
    ){
        return modelMapper.map(providerService.create(modelMapper.map(createProviderDto, Provider.class)), ProviderDto.class);
    }

    @Operation(summary = "Find Provider by Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Found",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProviderDto.class)
                    )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Provider Not Found",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @GetMapping("/{id}")
    ProviderDto find(
        @Parameter(name = "id", description = "Provider Id", example = "1")
        @PathVariable Long id
    ){
        ProviderDto providerDto = modelMapper.map(providerService.findById(id), ProviderDto.class);
        if(providerDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider Not Found");
        }
        return providerDto;
    }

    @Operation(summary = "Update Provider by Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Updated",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProviderDto.class)
                    )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Update Provider",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @PutMapping("/{id}")
    ProviderDto update(
        @Parameter(name = "id", description = "Provider Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Provider Object to Be Updated")
        @RequestBody @Valid UpdateProviderDto updateProviderDto
    ){
        return modelMapper.map(providerService.update(id, modelMapper.map(updateProviderDto, Provider.class) ), ProviderDto.class);
    }

    @Operation(summary = "Delete Provider By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Provider Deleted",
            content = {@Content(mediaType = "application/json")}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Delete Provider",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @DeleteMapping("/{id}")
    void delete(
        @Parameter(name = "id", description = "Provider Id", example = "1")
        @PathVariable Long id
    ){
        providerService.delete(id);
    }
}
