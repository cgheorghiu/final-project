package com.itschool.springapp.utils;

import com.itschool.springapp.entity.Cargo;
import com.itschool.springapp.entity.Pier;
import com.itschool.springapp.entity.Ship;
import com.itschool.springapp.model.CargoDTO;
import com.itschool.springapp.model.PierDTO;
import com.itschool.springapp.model.ShipDTO;

public class ShipModelConverter {

    private ShipModelConverter() {
        //needed for restricting the class instantiation
    }

    public static ShipDTO toShipDTO(Ship shipEntity) {
        CargoDTO cargoDTO = CargoModelConverter.toCargoDTO(shipEntity.getCargo());
        PierDTO pierDTO = PierModelConverter.toPierDTO(shipEntity.getPier());

        return new ShipDTO(
                shipEntity.getId(),
                shipEntity.getName(),
                shipEntity.getShipType(),
                shipEntity.getTonnage(),
                cargoDTO,
                pierDTO.id()
        );

    }

    public static Ship toShipEntity(ShipDTO shipDTO, Pier pier) {
        CargoDTO cargoDTO = shipDTO.cargo();
        Cargo cargo = CargoModelConverter.toCargoEntity(cargoDTO);

        Ship ship = new Ship();

        ship.setName(shipDTO.name());
        ship.setShipType(shipDTO.shipType());
        ship.setTonnage(shipDTO.tonnage());
        ship.setCargo(cargo);
        ship.setPier(pier);

        return ship;
    }
}
