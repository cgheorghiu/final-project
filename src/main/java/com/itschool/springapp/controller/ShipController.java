package com.itschool.springapp.controller;

import com.itschool.springapp.model.ShipDTO;
import com.itschool.springapp.model.ShipsDTO;
import com.itschool.springapp.service.ShipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ship Manager", description = "Ship Manager API that manipulates operations related to ship")
@RestController
@RequestMapping("ships")
public class ShipController {
    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @Operation(summary = "Find a ship by id", description = "Find a ship by their id and return the found ship")
    @GetMapping("{id}")
    public ShipDTO getShip(@PathVariable long id) {
        return shipService.getShip(id);
    }

    @Operation(summary = "Find all ships / Find all ships by a specific name", description = "Find all ships / Find all ships with a specific name in the database and return them in a list")
    @GetMapping
    public ShipsDTO findAllShips(@RequestParam(value = "name", required = false) String name) {
        return shipService.getAllShips(name);
    }

    @Operation(summary = "Add a new ship", description = "Add a new ship to the database and return the created ship")
    @PostMapping
    public ShipDTO createShip(@RequestBody ShipDTO ShipDTO) {
        return shipService.createShip(ShipDTO);
    }

    @Operation(summary = "Update a ship", description = "Update a ship by their id and return the updated ship")
    @PutMapping("{id}")
    public ShipDTO updateShip(@PathVariable long id, @RequestBody ShipDTO ShipDTO) {
        return shipService.updateShip(id, ShipDTO);
    }

    @Operation(summary = "Delete a ship", description = "Delete a ship by their id. An HTTP 200 OK response is returned if the ship was deleted successfully")
    @DeleteMapping("{id}")
    public void deleteShip(@PathVariable long id) {
        shipService.deleteShip(id);
    }
}
