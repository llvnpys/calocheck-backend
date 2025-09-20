package com.calocheck.calobackend.repository;

import com.calocheck.calobackend.domain.Menu;
import com.calocheck.calobackend.dto.MenuFilterDto;
import com.calocheck.calobackend.dto.MenuItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {


    @Query("""
        select new com.calocheck.calobackend.dto.MenuItemDto(
        m.id,
        m.name,
        n.calories,
        n.protein,
        n.fat,
        n.carbohydrate,
        n.size,
        n.price,
        n.caffeineMg
        )
        from Menu m
        join Nutrition n on n.menu = m
        where m.brand.id = :brandId
          and (:#{#f.caloriesMin} is null or n.calories     >= :#{#f.caloriesMin})
          and (:#{#f.caloriesMax} is null or n.calories     <= :#{#f.caloriesMax})
          and (:#{#f.proteinMin} is null or n.protein      >= :#{#f.proteinMin})
          and (:#{#f.proteinMax} is null or n.protein      <= :#{#f.proteinMax})
          and (:#{#f.fatMin}     is null or n.fat          >= :#{#f.fatMin})
          and (:#{#f.fatMax}     is null or n.fat          <= :#{#f.fatMax})
          and (:#{#f.carbMin}    is null or n.carbohydrate >= :#{#f.carbMin})
          and (:#{#f.carbMax}    is null or n.carbohydrate <= :#{#f.carbMax})
        """)
    List<MenuItemDto> findMenusByBrandAndFilter(@Param("brandId") UUID brandId,
                                                @Param("f") MenuFilterDto filter);

}
