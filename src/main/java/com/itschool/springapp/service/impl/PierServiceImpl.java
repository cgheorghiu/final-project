package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.Pier;
import com.itschool.springapp.exception.EntityNotFoundException;
import com.itschool.springapp.model.PierDTO;
import com.itschool.springapp.model.PiersDTO;
import com.itschool.springapp.repository.PierRepository;
import com.itschool.springapp.service.PierService;
import com.itschool.springapp.utils.PierModelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PierServiceImpl implements PierService {
    private final PierRepository pierRepository;

    @Override
    public PierDTO getPier(long id) {
        Pier foundPierEntity = pierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pier " + id + " not found in db!"));

        return PierModelConverter.toPierDTO(foundPierEntity);
    }

    @Override
    public PiersDTO getAllPiers() {
        List<Pier> allPierEntities = pierRepository.findAll();
        PiersDTO piers = new PiersDTO();

        piers.setPiers(allPierEntities.stream()
                .map(PierModelConverter::toPierDTO)
                .toList());

        return piers;
    }

    @Override
    public PierDTO createPier(PierDTO newPierDTO) {
        Pier pierEntitiy = PierModelConverter.toPierEntity(newPierDTO);
        Pier createdPierEntity = pierRepository.save(pierEntitiy);

        return PierModelConverter.toPierDTO(createdPierEntity);
    }

    @Override
    public PierDTO updatePier(long id, PierDTO updatedPierDTO) {
        Pier pierEntitiy = PierModelConverter.toPierEntity(updatedPierDTO);
        pierEntitiy.setId(id);

        Pier updatedPierEntity = pierRepository.save(pierEntitiy);

        return PierModelConverter.toPierDTO(updatedPierEntity);
    }

    @Override
    public void deletePier(long id) {
        pierRepository.deleteById(id);
    }
}
