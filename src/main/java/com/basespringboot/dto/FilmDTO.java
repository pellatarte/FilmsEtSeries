package com.basespringboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FilmDTO(
        Long id,
        @NotBlank String titre,
        @NotNull @Min(1888) Integer anneeSortie,
        @NotNull @Min(1) Integer dureeMinutes,
        @NotNull Long genreId
) {}
