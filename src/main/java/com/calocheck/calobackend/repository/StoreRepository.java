package com.calocheck.calobackend.repository;

import com.calocheck.calobackend.domain.Store;
import com.calocheck.calobackend.dto.StoreFilterDto;
import com.calocheck.calobackend.dto.StorePinDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("""
            select new com.calocheck.calobackend.dto.StorePinDto(
                    s.id, s.brand.id, s.name, s.brand.category, s.lat, s.lon, s.address
                  )
                  from Store s
                  where s.lat between :minLat and :maxLat
                    and s.lon between :minLon and :maxLon
                    and s.brand.category in :categories
                    and (
                      :hasMacro = false or exists (
                        select 1 from Menu m join Nutrition n on n.menu = m
                        where m.brand = s.brand
                            and (:#{#f.caloriesMin}  is null or n.calories     >= :#{#f.caloriesMin})
                            and (:#{#f.caloriesMax}  is null or n.calories     <= :#{#f.caloriesMax})
                            and (:#{#f.proteinMin}   is null or n.protein      >= :#{#f.proteinMin})
                            and (:#{#f.proteinMax}   is null or n.protein      <= :#{#f.proteinMax})
                            and (:#{#f.fatMin}       is null or n.fat          >= :#{#f.fatMin})
                            and (:#{#f.fatMax}       is null or n.fat          <= :#{#f.fatMax})
                            and (:#{#f.carbMin}      is null or n.carbohydrate >= :#{#f.carbMin})
                            and (:#{#f.carbMax}      is null or n.carbohydrate <= :#{#f.carbMax})
                      )
                    )
            """)
    List<StorePinDto> findStoresByFilter(
            @Param("categories") List<String> categories,
            @Param("minLat") Double minLat, @Param("maxLat") Double maxLat,
            @Param("minLon") Double minLon, @Param("maxLon") Double maxLon,
            @Param("hasMacro") Boolean hasMacro,
            @Param("f") StoreFilterDto filter
    );
}
