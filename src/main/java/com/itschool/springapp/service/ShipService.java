package com.itschool.springapp.service;

import com.itschool.springapp.model.ShipDTO;
import com.itschool.springapp.model.ShipsDTO;

public interface ShipService {

    /**
     * Retrieve a specific ship
     * @param id the id of the ship to retrieve
     * @return a ship deserialized into a {@link ShipDTO} object
     */
    ShipDTO getShip(long id);

    /**
     * Retrieve all ships
     * @return a list of {@link ShipsDTO} an object representing all ships
     */
    ShipsDTO getAllShips(String name);

    /**
     * Create a new ship
     * @param newShipDTO a {@link ShipDTO} object representing the new ship to create
     * @return the created ship as a {@link ShipDTO} object
     */
    ShipDTO createShip(ShipDTO newShipDTO);

    /**
     * Update an existing ship
     * @param id the id of the ship to update
     * @param updatedShipDTO a {@link ShipDTO} object representing the ship data to update
     * @return the updated ship as a {@link ShipDTO} object
     */
    ShipDTO updateShip(long id, ShipDTO updatedShipDTO);

    /**
     * Delete a ship
     * @param id the id of the ship to delete
     */
    void deleteShip(long id);

    /**
     * Retrieve all ships for a specific pier
     * @param pierId the id of the pier whose ships we want to retrieve
     * @return a list of {@link ShipDTO} objects representing all orders for the user
     */
    ShipsDTO findAllShipsByPierId(long pierId);

}
