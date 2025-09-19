package com.calocheck.calobackend.controller;

import com.calocheck.calobackend.dto.StoreFilterRequestDto;
import com.calocheck.calobackend.dto.StorePinDto;
import com.calocheck.calobackend.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping
    public List<StorePinDto> getAllStores() {
        return storeService.findAll();
    }

    @PostMapping("/filtered")
    public List<StorePinDto> getFilteredStores(@RequestBody StoreFilterRequestDto requestDto) {
        return storeService.findStoreByFilters(requestDto);
    }

}
