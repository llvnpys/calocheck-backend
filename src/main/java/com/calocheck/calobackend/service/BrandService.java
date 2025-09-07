package com.calocheck.calobackend.service;

import com.calocheck.calobackend.domain.Brand;
import com.calocheck.calobackend.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {
    private final BrandRepository brandRepository;

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findById(UUID id) {
        return brandRepository.findById(id);
    }

    public Optional<Brand> findByName(String name) {
        return  brandRepository.findByName(name);
    }
}
