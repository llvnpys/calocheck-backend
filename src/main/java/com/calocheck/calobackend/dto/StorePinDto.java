package com.calocheck.calobackend.dto;

import java.util.UUID;

public record StorePinDto(
        UUID id,
        UUID brandId,
        String name,
        String brandCategory,
        double lat,
        double lon,
        String address
) {
}