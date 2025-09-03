package com.calocheck.calobackend.repository;

import com.calocheck.calobackend.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {
    Page<Menu> findByBrandId(UUID brandId, Pageable pageable);
    Optional<Menu> findByBrandIdAndName(UUID brandId, String name);
    Page<Menu> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}
