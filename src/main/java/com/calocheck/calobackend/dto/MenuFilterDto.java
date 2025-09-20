package com.calocheck.calobackend.dto;


public record MenuFilterDto(
        Double caloriesMin, Double caloriesMax,
        Double proteinMin, Double proteinMax,
        Double fatMin, Double fatMax,
        Double carbMin, Double carbMax,
        Double centerLat, Double centerLon
) {
}
