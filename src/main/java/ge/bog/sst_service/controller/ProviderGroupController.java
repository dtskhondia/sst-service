package ge.bog.sst_service.controller;

import ge.bog.sst_service.dto.ProviderDto;
import ge.bog.sst_service.dto.ProviderGroupDto;
import ge.bog.sst_service.mapper.ProviderGroupMapper;
import ge.bog.sst_service.service.ProviderGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/providerGroups")
public class ProviderGroupController {
    @Autowired
    private ProviderGroupMapper providerGroupMapper;
    @Autowired
    private ProviderGroupService providerGroupService;

    @Operation(summary = "Create Provider Group")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Created",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProviderGroupDto.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Create",
            content = { @Content( mediaType = "application/json") }
        )
    })
    @PostMapping()
    ProviderGroupDto create(
        @Parameter(description = "Provider Group to Be Created")
        @RequestBody @Valid ProviderGroupDto providerGroupDto
    ){
        return providerGroupMapper.map(providerGroupService.create(providerGroupMapper.map(providerGroupDto)));
    }

    @Operation(summary = "Find Provider Group by Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProviderGroupDto.class)
                    )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @GetMapping("/{id}")
    ProviderGroupDto find(
        @Parameter(name = "id", description = "Provider Group Id", example = "1")
        @PathVariable Long id
    ){
        ProviderGroupDto providerGroupDto = providerGroupMapper.map(providerGroupService.findById(id));
        if(providerGroupDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider Group Not Found");
        }
        return providerGroupDto;
    }

    @Operation(summary = "Update Provider Group by Id")
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
    ProviderGroupDto update(
        @Parameter(name = "id", description = "Provider Group Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Provider Group Object to Be Updated")
        @RequestBody @Valid ProviderGroupDto providerGroupDto
    ){
        return providerGroupMapper.map(providerGroupService.update(id, providerGroupMapper.map(providerGroupDto) ) );
    }

    @Operation(summary = "Delete Provider Group By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Deleted",
            content = {@Content(mediaType = "application/json")}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Delete",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @DeleteMapping("/{id}")
    void delete(
        @Parameter(name = "id", description = "Provider Group Id", example = "1")
        @PathVariable Long id
    ){
        providerGroupService.delete(id);
    }
}