package com.calocheck.calobackend.dto;

import java.util.List;

public record StoreFilterDto(
        List<String> categories,
        Double caloriesMin, Double caloriesMax,
        Double proteinMin, Double proteinMax,
        Double fatMin, Double fatMax,
        Double carbMin, Double carbMax,
        Double centerLat, Double centerLon
) {
}