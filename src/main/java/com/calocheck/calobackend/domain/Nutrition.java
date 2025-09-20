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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nutrition {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    private String size;
    private Integer price;

    @Column(nullable = false)
    private Double calories;
    private Double protein;
    private Double fat;
    private Double carbohydrate;
    private Double sugar;

    @Column(name = "caffeine_mg")
    private Integer caffeineMg;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}