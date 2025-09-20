package com.calocheck.calobackend.service;

import com.calocheck.calobackend.dto.StoreFilterDto;
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

    public List<StorePinDto> findStoresByFilter(StoreFilterDto filter) {
        double lat = filter.centerLat() != null ? filter.centerLat() : DEFAULT_LAT;
        double lon = filter.centerLon() != null ? filter.centerLon() : DEFAULT_LON;

        // km → degree 근사치
        double latDegPerKm = 1.0 / 110.574;
        double lonDegPerKm = 1.0 / (111.320 * Math.cos(Math.toRadians(lat)));

        double dLat = HALF_SIDE_KM * latDegPerKm;
        double dLon = HALF_SIDE_KM * lonDegPerKm;

        double minLat = lat - dLat;
        double maxLat = lat + dLat;
        double minLon = lon - dLon;
        double maxLon = lon + dLon;

        // 카테고리 없으면 빈 리스트
        if (filter.categories() == null || filter.categories().isEmpty()) {
            return List.of();
        }

        // 매크로(영양) 조건이 하나라도 있으면 true
        boolean hasMacro =
                filter.proteinMin()  != null || filter.proteinMax()  != null ||
                        filter.fatMin()      != null || filter.fatMax()      != null ||
                        filter.carbMin()     != null || filter.carbMax()     != null ||
                        filter.caloriesMin() != null || filter.caloriesMax() != null;

        return storeRepository.findStoresByFilter(
                filter.categories(),
                minLat, maxLat, minLon, maxLon,
                hasMacro,
                filter
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