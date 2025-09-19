package com.calocheck.calobackend.service;

import com.calocheck.calobackend.dto.StoreFilterRequestDto;
import com.calocheck.calobackend.dto.StorePinDto;
import com.calocheck.calobackend.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    // 기본 위치 : 선릉역
    private static final double DEFAULT_LAT = 37.504;
    private static final double DEFAULT_LON = 127.048;
    // 고정 반경(km) → bbox
    private static final double HALF_SIDE_KM = 1.0;

    public List<StorePinDto> findStoreByFilters(StoreFilterRequestDto req) {
        double lat = req.centerLat() != null ? req.centerLat() : DEFAULT_LAT;
        double lon = req.centerLon() != null ? req.centerLon() : DEFAULT_LON;

        // km → degree 근사치 (위도/경도 변환)
        // 위도 1° ≈ 110.574km, 경도 1° ≈ 111.320 * cos(lat) km
        double latDegPerKm = 1.0 / 110.574;
        double lonDegPerKm = 1.0 / (111.320 * Math.cos(Math.toRadians(lat)));

        double dLat = HALF_SIDE_KM * latDegPerKm;
        double dLon = HALF_SIDE_KM * lonDegPerKm;

        double minLat = lat - dLat;
        double maxLat = lat + dLat;
        double minLon = lon - dLon;
        double maxLon = lon + dLon;

        // 카테고리가 아예 없으면 아무 매장도 반환하지 않음
        if (req.categories() == null ||  req.categories().isEmpty()) {
            return List.of();
        }

        // Determine if any macro filter is present
        boolean hasMacro =
                req.proteinMin() != null || req.proteinMax() != null ||
                req.fatMin() != null || req.fatMax() != null ||
                req.carbMin() != null || req.carbMax() != null ||
                req.caloriesMin() != null || req.caloriesMax() != null;

        return storeRepository.findStoreByFilters(
                req.categories(),
                minLat, maxLat, minLon, maxLon,
                hasMacro,
                req.caloriesMin(), req.caloriesMax(),
                req.proteinMin(),  req.proteinMax(),
                req.fatMin(),      req.fatMax(),
                req.carbMin(),     req.carbMax()
        );
    }


    public List<StorePinDto> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(s -> new StorePinDto(
                        s.getId(),
                        s.getBrand().getId(),
                        s.getName(),
                        s.getBrand().getCategory(),
                        s.getLat(),
                        s.getLon(),
                        s.getAddress()
                ))
                .toList();
    }
}
