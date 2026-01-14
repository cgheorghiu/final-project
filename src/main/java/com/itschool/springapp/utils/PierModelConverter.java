package com.itschool.springapp.utils;

import com.itschool.springapp.entity.Pier;
import com.itschool.springapp.model.PierDTO;
import org.springframework.stereotype.Component;

@Component
public class PierModelConverter {

    public PierDTO toPierDTO(Pier pierEntity) {
        return new PierDTO(
                pierEntity.getId(),
                pierEntity.getName(),
                pierEntity.getTonnageCapacity()
        );
    }

    public Pier toPierEntity(PierDTO pierDTO) {

        Pier pier = new Pier();
        pier.setName(pierDTO.name());
        pier.setTonnageCapacity(pierDTO.tonnageCapacity());

        return pier;
    }
}
