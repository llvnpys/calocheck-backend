package com.calocheck.calobackend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "menu", uniqueConstraints = {
        @UniqueConstraint(name = "ux_menu_brand_name", columnNames = {"brand_id", "name"})
}, indexes = {
        @Index(name = "ix_menu_name", columnList = "name")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Menu {

    @Id @GeneratedValue @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false, length = 255)
    private String name;

    private Integer price;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}