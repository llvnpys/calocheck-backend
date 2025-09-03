package com.calocheck.calobackend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "brand", indexes = {
        @Index(name = "ux_brand_name", columnList = "name", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class Brand {

    @Id @GeneratedValue @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)      // 'cafe','restaurant','bakery','etc'
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}