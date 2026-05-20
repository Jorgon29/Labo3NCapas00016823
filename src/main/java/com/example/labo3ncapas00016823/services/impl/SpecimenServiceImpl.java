package com.example.labo3ncapas00016823.services.impl;

import com.example.labo3ncapas00016823.common.mappers.SpecimenMapper;
import com.example.labo3ncapas00016823.domain.dto.request.CreateSpecimenRequest;
import com.example.labo3ncapas00016823.domain.dto.request.UpdateSpecimenRequest;
import com.example.labo3ncapas00016823.domain.dto.response.SpecimenResponse;
import com.example.labo3ncapas00016823.domain.entities.Specimen;
import com.example.labo3ncapas00016823.exceptions.ResourceNotFoundException;
import com.example.labo3ncapas00016823.repositories.SpecimenRepository;
import com.example.labo3ncapas00016823.services.interfaces.SpecimenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return specimenMapper.toDto(
                specimenRepository.save(specimenMapper.toEntityCreate(request))
        );
    }

    @Override
    public List<SpecimenResponse> getAllSpecimens() {
        List<Specimen> specimens = specimenRepository.findAll();
        if (specimens.isEmpty())
            throw new ResourceNotFoundException("No specimens are registered in Hyrule");

        return specimens.stream()
                .map(specimenMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenMapper.toDto(specimenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen not found in Hyrule Records"))
        );
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        this.getSpecimenById(id);
        return specimenMapper.toDto(specimenRepository.save(specimenMapper.toEntityUpdate(request, id)));
    }

    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}
