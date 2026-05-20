package com.example.labo3ncapas00016823.services.interfaces;

import com.example.labo3ncapas00016823.domain.dto.request.CreateSpecimenRequest;
import com.example.labo3ncapas00016823.domain.dto.request.UpdateSpecimenRequest;
import com.example.labo3ncapas00016823.domain.dto.response.PageableResponse;
import com.example.labo3ncapas00016823.domain.dto.response.specimen.SpecimenResponse;

import java.util.List;
import java.util.UUID;

public interface SpecimenService {
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request);
    public PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder);
    public SpecimenResponse getSpecimenById(UUID id);
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request);
    public SpecimenResponse deleteSpecimen(UUID id);
}
