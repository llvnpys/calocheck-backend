package com.calocheck.calobackend.repository;

import com.calocheck.calobackend.domain.Store;
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
                          and (:calMin is null or n.calories >= :calMin)
                          and (:calMax is null or n.calories <= :calMax)
                          and (:proMin is null or n.protein  >= :proMin)
                          and (:proMax is null or n.protein  <= :proMax)
                          and (:fatMin is null or n.fat      >= :fatMin)
                          and (:fatMax is null or n.fat      <= :fatMax)
                          and (:carbMin is null or n.carbohydrate >= :carbMin)
                          and (:carbMax is null or n.carbohydrate <= :carbMax)
                      )
                    )
            """)
    List<StorePinDto> findStoreByFilters(
            @Param("categories") List<String> categories,

            @Param("minLat") Double minLat, @Param("maxLat") Double maxLat,
            @Param("minLon") Double minLon, @Param("maxLon") Double maxLon,
            @Param("hasMacro") Boolean hasMacro,
            @Param("calMin")  Double calMin,  @Param("calMax")  Double calMax,
            @Param("proMin")  Double proMin,  @Param("proMax")  Double proMax,
            @Param("fatMin")  Double fatMin,  @Param("fatMax")  Double fatMax,
            @Param("carbMin") Double carbMin, @Param("carbMax") Double carbMax
    );
}
