package com.calocheck.calobackend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "nutrition", uniqueConstraints = {
        @UniqueConstraint(name = "ux_nutrition_menu_size", columnNames = {"menu_id", "size"})
}, indexes = {
        @Index(name = "ix_nutrition_menu", columnList = "menu_id")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Nutrition {

    @Id @GeneratedValue @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private Integer calories;

    @Column(precision = 6, scale = 1)
    private BigDecimal protein;

    @Column(precision = 6, scale = 1)
    private BigDecimal fat;

    @Column(precision = 6, scale = 1)
    private BigDecimal carbohydrate;

    @Column(precision = 6, scale = 1)
    private BigDecimal sugar;

    @Column(name = "caffeine_mg")
    private Integer caffeineMg;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}