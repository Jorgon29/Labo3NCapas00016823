package com.example.labo3ncapas00016823.controllers;

import com.example.labo3ncapas00016823.domain.dto.request.CreateSpecimenRequest;
import com.example.labo3ncapas00016823.domain.dto.request.UpdateSpecimenRequest;
import com.example.labo3ncapas00016823.domain.dto.response.GeneralResponse;
import com.example.labo3ncapas00016823.services.interfaces.SpecimenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/specimens")
@RequiredArgsConstructor
public class SpecimenController {
    private final SpecimenService specimenService;

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllSpecimens(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        return buildResponse(
                "Specimens found",
                HttpStatus.OK,
                specimenService.getAllSpecimens(page, size, sortBy, sortOrder)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getSpecimenById(
            @PathVariable UUID id){
        return buildResponse(
                "Specimen found for id: " + id,
                HttpStatus.OK,
                specimenService.getSpecimenById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateSpecimen(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateSpecimenRequest request
            ){
        return buildResponse(
                "Updated specimen with id: " + id,
                HttpStatus.OK,
                specimenService.updateSpecimen(id, request)
        );
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createSpecimen(
            @RequestBody @Valid CreateSpecimenRequest request
    ){
        return buildResponse(
                "Created specimen",
                HttpStatus.CREATED,
                specimenService.createSpecimen(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> deleteSpecimen(
            @PathVariable UUID id
    ){
        return buildResponse(
                "Deleted specimen with id: " + id,
                HttpStatus.OK,
                specimenService.deleteSpecimen(id)
        );
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity
                .status(status)
                .body(GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
                );
    }

}
