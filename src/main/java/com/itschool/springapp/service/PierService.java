package com.itschool.springapp.service;

import com.itschool.springapp.model.PierDTO;
import com.itschool.springapp.model.PiersDTO;

public interface PierService {

    /**
     * Retrieve a specific pier
     * @param id the id of the pier to retrieve
     * @return a pier deserialized into a {@link PierDTO} object
     */
    PierDTO getPier(long id);

    /**
     * Retrieve all piers
     * @return a list of {@link PierDTO} objects representing all piers
     */
    PiersDTO getAllPiers();

    /**
     * Create a new pier
     * @param newPierDTO a {@link PierDTO} object representing the new pier to create
     * @return the created pier as a {@link PierDTO} object
     */
    PierDTO createPier(PierDTO newPierDTO);

    /**
     * Update an existing pier
     * @param id the id of the pier to update
     * @param updatedPierDTO a {@link PierDTO} object representing the pier data to update
     * @return the updated pier as a {@link PierDTO} object
     */
    PierDTO updatePier(long id, PierDTO updatedPierDTO);

    /**
     * Delete a pier
     * @param id the id of the pier to delete
     */
    void deletePier(long id);
}
