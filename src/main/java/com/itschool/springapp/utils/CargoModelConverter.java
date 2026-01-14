package com.itschool.springapp.utils;

import com.itschool.springapp.entity.Cargo;
import com.itschool.springapp.model.CargoDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CargoModelConverter {

    public CargoDTO toCargoDTO(Cargo cargoEntity) {
        return Optional.ofNullable(cargoEntity)
                .map(cargo -> new CargoDTO(
                        cargo.getId(),
                        cargo.getCargoType(),
                        cargo.getDescription(),
                        cargo.getWeight()))
                .orElse(null);
    }

    public Cargo toCargoEntity(CargoDTO cargoDTO) {
        if (cargoDTO == null) {
            return null;
        }

        Cargo cargo = new Cargo();
        cargo.setCargoType(cargoDTO.cargoType());
        cargo.setDescription(cargoDTO.description());
        cargo.setWeight(cargoDTO.weight());
        return cargo;
    }
}
