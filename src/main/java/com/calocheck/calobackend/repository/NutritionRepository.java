package com.calocheck.calobackend.repository;

import com.calocheck.calobackend.domain.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NutritionRepository extends JpaRepository<Nutrition, UUID> {
    List<Nutrition> findByMenuId(UUID menuId);
    Optional<Nutrition> findByMenuIdAndSize(UUID menuId, String size);
}
