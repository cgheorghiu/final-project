package com.itschool.springapp.controller;

import com.itschool.springapp.model.PierDTO;
import com.itschool.springapp.model.PiersDTO;
import com.itschool.springapp.model.ShipsDTO;
import com.itschool.springapp.service.PierService;
import com.itschool.springapp.service.ShipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pier Manager", description = "Pier Manager API that manipulates operations related to pier")
@RestController
@RequestMapping("piers")
@RequiredArgsConstructor
public class PierController {
    private final PierService pierService;
    private final ShipService shipService;

    @Operation(summary = "Find a pier by id", description = "Find a pier by their id and return the found pier")
    @GetMapping("{id}")
    public PierDTO getPier(@PathVariable long id) {
        return pierService.getPier(id);
    }

    @Operation(summary = "Find all piers", description = "Find all piers in the database and return them in a list")
    @GetMapping
    public PiersDTO findAllPiers() {
        return pierService.getAllPiers();
    }

    @Operation(summary = "Add a new pier", description = "Add a new pier to the database and return the created pier")
    @PostMapping
    public PierDTO createPier(@RequestBody PierDTO pierDTO) {
        return pierService.createPier(pierDTO);
    }

    @Operation(summary = "Update a pier", description = "Update a pier by their id and return the updated pier")
    @PutMapping("{id}")
    public PierDTO updatePier(@PathVariable long id, @RequestBody PierDTO pierDTO) {
        return pierService.updatePier(id, pierDTO);
    }

    @Operation(summary = "Delete a pier", description = "Delete a pier by their id. An HTTP 200 OK response is returned if the pier was deleted successfully")
    @DeleteMapping("{id}")
    public void deletePier(@PathVariable long id) {
        pierService.deletePier(id);
    }

    @Operation(summary = "Find all ships by pier id", description = "Find all ships that are docked at a specific pier in the database and return them in a list")
    @GetMapping("{id}/ships")
    public ShipsDTO findAllShipsByPierId(@PathVariable long id) {
        return shipService.findAllShipsByPierId(id);
    }

}
