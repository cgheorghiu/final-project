package com.itschool.springapp.utils;

import com.itschool.springapp.entity.Cargo;
import com.itschool.springapp.entity.Pier;
import com.itschool.springapp.entity.Ship;
import com.itschool.springapp.model.CargoDTO;
import com.itschool.springapp.model.PierDTO;
import com.itschool.springapp.model.ShipDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShipModelConverter {
    private final PierModelConverter pierModelConverter;
    private final CargoModelConverter cargoModelConverter;

    public ShipDTO toShipDTO(Ship shipEntity) {
        CargoDTO cargoDTO = cargoModelConverter.toCargoDTO(shipEntity.getCargo());
        PierDTO pierDTO = pierModelConverter.toPierDTO(shipEntity.getPier());

        return new ShipDTO(
                shipEntity.getId(),
                shipEntity.getName(),
                shipEntity.getShipType(),
                shipEntity.getTonnage(),
                cargoDTO,
                pierDTO
        );

    }

    public Ship toShipEntity(ShipDTO shipDTO) {
        CargoDTO cargoDTO = shipDTO.cargo();
        Cargo cargo = cargoModelConverter.toCargoEntity(cargoDTO);
        PierDTO pierDTO = shipDTO.pierDTO();
        Pier pier =  pierModelConverter.toPierEntity(pierDTO);

        Ship ship = new Ship();

        ship.setName(shipDTO.name());
        ship.setShipType(shipDTO.shipType());
        ship.setTonnage(shipDTO.tonnage());
        ship.setCargo(cargo);
        ship.setPier(pier);

        return ship;
    }
}
