package com.calocheck.calobackend.service;

import com.calocheck.calobackend.domain.Store;
import com.calocheck.calobackend.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    public List<Store> findByBrandId(UUID brandId) {
        return storeRepository.findByBrandId(brandId);
    }
}
