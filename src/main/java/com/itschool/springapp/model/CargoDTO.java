package com.itschool.springapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CargoDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        String cargoType,
        String description,
        Long weight
) {
}
