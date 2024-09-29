package ge.bog.sst_service.controller;

import ge.bog.sst_service.domain.Address;
import ge.bog.sst_service.dto.AddressDto;
import ge.bog.sst_service.dto.CreateAddressDto;
import ge.bog.sst_service.dto.UpdateAddressDto;
import ge.bog.sst_service.service.AddressService;
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
@RequestMapping("/addresses")
public class AddressController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressService addressService;

    @Operation(summary = "Create Address")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Address Created",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AddressDto.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Create Address",
            content = { @Content( mediaType = "application/json") }
        )
    })
    @PostMapping()
    AddressDto create(
        @Parameter(description = "Address Object to Be Created")
        @RequestBody @Valid CreateAddressDto createAddressDto
    ){
        return modelMapper.map(addressService.create(modelMapper.map(createAddressDto, Address.class)), AddressDto.class);
    }

    @Operation(summary = "Find Address by Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Address Found",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AddressDto.class)
                    )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Address Not Found",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @GetMapping("/{id}")
    AddressDto find(
        @Parameter(name = "id", description = "Address Id", example = "1")
        @PathVariable Long id
    ){
        AddressDto addressDto = modelMapper.map(addressService.findById(id), AddressDto.class);
        if(addressDto==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address Not Found");
        }
        return addressDto;
    }

    @Operation(summary = "Update Address by Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Address Updated",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AddressDto.class)
                    )
            }
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Update Address",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @PutMapping("/{id}")
    AddressDto update(
        @Parameter(name = "id", description = "Address Id", example = "1")
        @PathVariable Long id,
        @Parameter(description = "Address Object to Be Updated")
        @RequestBody @Valid UpdateAddressDto updateAddressDto
    ){
        return modelMapper.map(addressService.update(id, modelMapper.map(updateAddressDto, Address.class) ), AddressDto.class);
    }

    @Operation(summary = "Delete Address By Id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Address Deleted",
            content = {@Content(mediaType = "application/json")}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Failed to Delete Address",
            content = {@Content(mediaType = "application/json")}
        )
    })
    @DeleteMapping("/{id}")
    void delete(
        @Parameter(name = "id", description = "Address Id", example = "1")
        @PathVariable Long id
    ){
        addressService.delete(id);
    }
}
