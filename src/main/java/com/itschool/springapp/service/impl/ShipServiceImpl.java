package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.Pier;
import com.itschool.springapp.entity.Ship;
import com.itschool.springapp.exception.EntityNotFoundException;
import com.itschool.springapp.model.ShipDTO;
import com.itschool.springapp.model.ShipsDTO;
import com.itschool.springapp.repository.PierRepository;
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
    private final PierRepository pierRepository;

    @Override
    public ShipDTO getShip(long id) {
        Ship foundShipEntity = shipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ship " + id + " not found in db!"));

        return ShipModelConverter.toShipDTO(foundShipEntity);
    }

    @Override
    public ShipsDTO getAllShips(String name) {
        List<Ship> allShipEntities = name == null ? shipRepository.findAll() : shipRepository.findAllByName(name);

        ShipsDTO shipsDTO = new ShipsDTO();
        shipsDTO.setShips(allShipEntities.stream()
                .map(ShipModelConverter::toShipDTO)
                .toList());

        return shipsDTO;
    }

    @Override
    public ShipDTO createShip(ShipDTO newShipDTO) {
        Pier pierEntity = pierRepository.findById(newShipDTO.pierId()).orElseThrow(() ->
                new EntityNotFoundException("Pier not found in db!"));

        Ship shipEntity = ShipModelConverter.toShipEntity(newShipDTO, pierEntity);

        Ship createdShipEntity = shipRepository.save(shipEntity);

        return ShipModelConverter.toShipDTO(createdShipEntity);
    }

    @Override
    public ShipDTO updateShip(long id, ShipDTO updatedShipDTO) {
        Pier pierEntity = pierRepository.findById(updatedShipDTO.pierId()).orElseThrow(() ->
                new EntityNotFoundException("Pier not found in db!"));
        Ship shipEntity = ShipModelConverter.toShipEntity(updatedShipDTO, pierEntity);
        shipEntity.setId(id);

        Ship updatedShipEntity = shipRepository.save(shipEntity);

        return ShipModelConverter.toShipDTO(updatedShipEntity);
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
                .map(ShipModelConverter::toShipDTO)
                .toList());

        return shipsDTO;
    }

}
