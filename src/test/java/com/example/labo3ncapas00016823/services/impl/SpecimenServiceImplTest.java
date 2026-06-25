package com.example.labo3ncapas00016823.services.impl;

import com.example.labo3ncapas00016823.common.mappers.SpecimenMapper;
import com.example.labo3ncapas00016823.domain.dto.request.CreateSpecimenRequest;
import com.example.labo3ncapas00016823.domain.dto.response.specimen.SpecimenResponse;
import com.example.labo3ncapas00016823.domain.entities.Specimen;
import com.example.labo3ncapas00016823.exceptions.ResourceNotFoundException;
import com.example.labo3ncapas00016823.repositories.SpecimenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpecimenServiceImplTest {

    @Mock
    private SpecimenRepository specimenRepository;
    @Mock
    private SpecimenMapper specimenMapper;
    @InjectMocks
    private SpecimenServiceImpl specimenService;

    @Test
    void createSpecimen_shouldSaveEntityAndReturnMappedResponse() {
        CreateSpecimenRequest request = mock(CreateSpecimenRequest.class);
        Specimen entityToSave = mock(Specimen.class);
        Specimen savedEntity = mock(Specimen.class);
        SpecimenResponse expectedResponse = mock(SpecimenResponse.class);

        when(specimenMapper.toEntityCreate(request)).thenReturn(entityToSave);
        when(specimenRepository.save(entityToSave)).thenReturn(savedEntity);
        when(specimenMapper.toDto(savedEntity)).thenReturn(expectedResponse);

        SpecimenResponse actualResponse = specimenService.createSpecimen(request);

        assertSame(expectedResponse, actualResponse);
        verify(specimenMapper).toEntityCreate(request);
        verify(specimenRepository).save(entityToSave);
        verify(specimenMapper).toDto(savedEntity);
    }

    @Test
    void getSpecimenById_shouldThrowResourceNotFoundException_whenSpecimenDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(specimenRepository.findById(id)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> specimenService.getSpecimenById(id)
        );

        assertEquals("Specimen not found in Sheikah Slate records", exception.getMessage());
        verify(specimenRepository).findById(id);
        verifyNoInteractions(specimenMapper);
    }

}
