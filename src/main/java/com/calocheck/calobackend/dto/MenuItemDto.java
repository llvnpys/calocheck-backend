package com.calocheck.calobackend.dto;

import java.util.UUID;

public record MenuItemDto(
        UUID id,
        String name,
        Double calories,
        Double protein,
        Double fat,
        Double carbohydrate,
        String size,
        Integer price,
        Integer caffeineMg
) {
}
