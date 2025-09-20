package com.calocheck.calobackend.service;

import com.calocheck.calobackend.dto.MenuFilterDto;
import com.calocheck.calobackend.dto.MenuItemDto;
import com.calocheck.calobackend.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final MenuService menuService;
    private final BrandRepository brandRepository;

    public List<MenuItemDto> findMenusByBrandAndFilter(UUID brandId, MenuFilterDto filter) {
        return menuService.findMenusByBrandAndFilter(brandId, filter);
    }
}
