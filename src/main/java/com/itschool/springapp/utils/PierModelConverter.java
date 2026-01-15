package com.itschool.springapp.utils;

import com.itschool.springapp.entity.Pier;
import com.itschool.springapp.model.PierDTO;

public class PierModelConverter {

    private PierModelConverter() {
        //needed for restricting the class instantiation
    }

    public static PierDTO toPierDTO(Pier pierEntity) {
        return new PierDTO(
                pierEntity.getId(),
                pierEntity.getName(),
                pierEntity.getTonnageCapacity()
        );
    }

    public static Pier toPierEntity(PierDTO pierDTO) {

        Pier pier = new Pier();
        pier.setName(pierDTO.name());
        pier.setTonnageCapacity(pierDTO.tonnageCapacity());

        return pier;
    }
}
