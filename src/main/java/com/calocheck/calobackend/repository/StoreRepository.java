package com.calocheck.calobackend.repository;

import com.calocheck.calobackend.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByBrandId(UUID brandId);

    Page<Store> findByBrandId(UUID brnadId, Pageable pageable);
}
