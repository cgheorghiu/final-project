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
    private final PierModelConverter pierModelConverter;

    @Override
    public PierDTO getPier(long id) {
        Pier foundPierEntity = pierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pier " + id + " not found in db!"));

        return pierModelConverter.toPierDTO(foundPierEntity);
    }

    @Override
    public PiersDTO getAllPiers() {
        List<Pier> allPierEntities = pierRepository.findAll();
        PiersDTO piers = new PiersDTO();

        piers.setPiers(allPierEntities.stream()
                .map(pierModelConverter::toPierDTO)
                .toList());

        return piers;
    }

    @Override
    public PierDTO createPier(PierDTO newPierDTO) {
        Pier pierEntitiy = pierModelConverter.toPierEntity(newPierDTO);
        Pier createdPierEntity = pierRepository.save(pierEntitiy);

        return pierModelConverter.toPierDTO(createdPierEntity);
    }

    @Override
    public PierDTO updatePier(long id, PierDTO updatedPierDTO) {
        Pier pierEntitiy = pierModelConverter.toPierEntity(updatedPierDTO);
        pierEntitiy.setId(id);

        Pier updatedPierEntity = pierRepository.save(pierEntitiy);

        return pierModelConverter.toPierDTO(updatedPierEntity);
    }

    @Override
    public void deletePier(long id) {
        pierRepository.deleteById(id);
    }
}
