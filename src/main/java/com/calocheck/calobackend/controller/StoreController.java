package com.calocheck.calobackend.controller;

import com.calocheck.calobackend.domain.Brand;
import com.calocheck.calobackend.domain.Store;
import com.calocheck.calobackend.dto.StorePinDto;
import com.calocheck.calobackend.service.BrandService;
import com.calocheck.calobackend.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public List<StorePinDto> getAllStores() {
        return storeService.findAll().stream()
                .map(s -> new StorePinDto(
                        s.getId(),
                        s.getBrand().getId(),
                        s.getName(),
                        s.getLat(),
                        s.getLon(),
                        s.getAddress()
                ))
                .toList();
    }


}
