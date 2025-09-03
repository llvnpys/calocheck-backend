package com.calocheck.calobackend.repository;


import com.calocheck.calobackend.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BrandRepository extends JpaRepository<Brand, UUID> {
    Optional<Brand> findByName(String name);
    boolean existsByName(String name);
}
