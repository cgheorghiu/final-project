package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.Pier;
import com.itschool.springapp.exception.EntityNotFoundException;
import com.itschool.springapp.model.PierDTO;
import com.itschool.springapp.model.PiersDTO;
import com.itschool.springapp.repository.PierRepository;
import com.itschool.springapp.service.PierService;
import com.itschool.springapp.utils.PierModelConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PierServiceImplTest {
    private static final long PIER_ID = 1L;
    private static final String PIER_NAME = "Mock Pier";
    private static final long PIER_TONNAGE = 1000L;

    @Mock
    private PierRepository pierRepository;

    private PierService pierService;

    @BeforeEach
    void setUp() {
        pierService = new PierServiceImpl(pierRepository);
    }

    @Test
    void getPier() {
        Pier pier = new Pier();
        pier.setId(PIER_ID);

        Mockito.when(pierRepository.findById(PIER_ID)).thenReturn(Optional.of(pier));

        PierDTO pierDTO = pierService.getPier(PIER_ID);

        assertNotNull(pierDTO);
        Mockito.verify(pierRepository).findById(PIER_ID);
    }

    @Test
    void getPier_pierNotFound() {

        Mockito.when(pierRepository.findById(PIER_ID)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> pierService.getPier(PIER_ID));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Pier 1 not found in db!", exception.getReason());
    }

    @Test
    void getAllPiers() {
        Mockito.when(pierRepository.findAll()).thenReturn(List.of(new Pier(), new Pier()));

        PiersDTO piersDTO = pierService.getAllPiers();

        assertNotNull(piersDTO);
        assertEquals(2, piersDTO.getPiers().size());
        Mockito.verify(pierRepository).findAll();
    }

    @Test
    void createPier() {
        PierDTO pierDTO = new PierDTO(
                PIER_ID,
                PIER_NAME,
                PIER_TONNAGE
        );
        Pier pierEntity = PierModelConverter.toPierEntity(pierDTO);

        Mockito.when(pierRepository.save(Mockito.argThat(p ->
                PIER_NAME.equals(p.getName()) &&
                        PIER_TONNAGE == p.getTonnageCapacity()
        ))).thenReturn(pierEntity);

        PierDTO result = pierService.createPier(pierDTO);

        assertNotNull(result);
        assertEquals(PIER_NAME, result.name());
        assertEquals(PIER_TONNAGE, result.tonnageCapacity());

        Mockito.verify(pierRepository, Mockito.times(1))
                .save(Mockito.argThat(p ->
                        PIER_NAME.equals(p.getName()) &&
                                PIER_TONNAGE == p.getTonnageCapacity()
                ));
    }

    @Test
    void updatePier_pierNotFound() {
        PierDTO updatedPierDTO = new PierDTO(
                PIER_ID,
                PIER_NAME,
                PIER_TONNAGE
        );

        Mockito.when(pierRepository.existsById(PIER_ID)).thenReturn(false);
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> pierService.updatePier(PIER_ID, updatedPierDTO));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Pier 1 not found in db!", exception.getReason());
    }

    @Test
    void updatePier() {
        PierDTO updatedPierDTO = new PierDTO(
                PIER_ID,
                PIER_NAME,
                PIER_TONNAGE
        );
        Pier updatedPier = PierModelConverter.toPierEntity(updatedPierDTO);
        updatedPier.setId(PIER_ID);

        Mockito.when(pierRepository.existsById(PIER_ID)).thenReturn(true);
        Mockito.when(pierRepository.save(
                Mockito.argThat(
                        pier -> pier.getId() == PIER_ID
                ))).thenReturn(updatedPier);

        PierDTO pierDTO = pierService.updatePier(PIER_ID, updatedPierDTO);

        assertNotNull(pierDTO);
        assertEquals(PIER_ID, pierDTO.id());
        assertEquals(PIER_NAME, pierDTO.name());
        assertEquals(PIER_TONNAGE, pierDTO.tonnageCapacity());

        Mockito.verify(pierRepository).existsById(PIER_ID);
        Mockito.verify(pierRepository).save(Mockito.argThat(
                pier -> pier.getId() == PIER_ID
        ));
    }

    @Test
    void deletePier() {
        pierService.deletePier(PIER_ID);

        Mockito.verify(pierRepository).deleteById(PIER_ID);
    }
}