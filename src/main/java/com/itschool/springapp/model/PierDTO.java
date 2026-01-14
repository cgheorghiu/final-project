package com.itschool.springapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PierDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        String name,
        Long tonnageCapacity
        ) {
}
