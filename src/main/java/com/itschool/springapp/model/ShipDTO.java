package com.itschool.springapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShipDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        String name,
        String shipType,
        Long tonnage,
        CargoDTO cargo,
        PierDTO pierDTO
) {
}
