package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.Ship;
import com.itschool.springapp.exception.EntityNotFoundException;
import com.itschool.springapp.model.ShipDTO;
import com.itschool.springapp.model.ShipsDTO;
import com.itschool.springapp.repository.ShipRepository;
import com.itschool.springapp.service.ShipService;
import com.itschool.springapp.utils.ShipModelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;
    private final ShipModelConverter shipModelConverter;

    @Override
    public ShipDTO getShip(long id) {
        Ship foundShipEntity = shipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ship " + id + " not found in db!"));

        return shipModelConverter.toShipDTO(foundShipEntity);
    }

    @Override
    public ShipsDTO getAllShips(String name) {
        List<Ship> allShipEntities = name == null ? shipRepository.findAll() : shipRepository.findAllByName(name);

        ShipsDTO shipsDTO = new ShipsDTO();
        shipsDTO.setShips(allShipEntities.stream()
                .map(shipModelConverter::toShipDTO)
                .toList());

        return shipsDTO;
    }

    @Override
    public ShipDTO createShip(ShipDTO newShipDTO) {
        Ship shipEntity = shipModelConverter.toShipEntity(newShipDTO);

        Ship createdShipEntity = shipRepository.save(shipEntity);

        return shipModelConverter.toShipDTO(createdShipEntity);
    }

    @Override
    public ShipDTO updateShip(long id, ShipDTO updatedShipDTO) {
        Ship shipEntity = shipModelConverter.toShipEntity(updatedShipDTO);
        shipEntity.setId(id);

        Ship updatedShipEntity = shipRepository.save(shipEntity);

        return shipModelConverter.toShipDTO(updatedShipEntity);
    }

    @Override
    public void deleteShip(long id) {
        shipRepository.deleteById(id);
    }

    @Override
    public ShipsDTO findAllShipsByPierId(long pierId) {
        ShipsDTO shipsDTO = new ShipsDTO();

        shipsDTO.setShips(shipRepository.findShipByPier_Id(pierId)
                .stream()
                .map(shipModelConverter::toShipDTO)
                .toList());

        return shipsDTO;
    }

}
