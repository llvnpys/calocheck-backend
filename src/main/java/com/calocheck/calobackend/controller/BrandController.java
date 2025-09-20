package com.calocheck.calobackend.controller;

import com.calocheck.calobackend.domain.Brand;
import com.calocheck.calobackend.dto.MenuFilterDto;
import com.calocheck.calobackend.dto.MenuItemDto;
import com.calocheck.calobackend.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping("/{brandId}/menus/filtered")
    public ResponseEntity<List<MenuItemDto>> findMenusByBrandAndFilter(
            @PathVariable UUID brandId,
            @RequestBody MenuFilterDto filter
    ) {
        List<MenuItemDto> menus = brandService.findMenusByBrandAndFilter(brandId, filter);
        return ResponseEntity.ok(menus);
    }
}
