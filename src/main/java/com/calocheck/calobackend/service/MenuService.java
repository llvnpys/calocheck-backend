package com.calocheck.calobackend.service;

import com.calocheck.calobackend.dto.MenuFilterDto;
import com.calocheck.calobackend.dto.MenuItemDto;
import com.calocheck.calobackend.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepository menuRepository;

    public List<MenuItemDto> findMenusByBrandAndFilter(UUID brandId, MenuFilterDto filter) {

        return menuRepository.findMenusByBrandAndFilter(brandId, filter).stream()
                .map(menuItem -> new MenuItemDto(
                        menuItem.id(),
                        menuItem.name(),
                        menuItem.calories(),
                        menuItem.protein(),
                        menuItem.fat(),
                        menuItem.carbohydrate(),
                        menuItem.size(),
                        menuItem.price(),
                        menuItem.caffeineMg()
                )).toList();

    }

}
