package com.nasa.stocktrack.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(ProductEntityListener.class)
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String code;

    private String brand;

    private String image;

    private Integer total;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "id.productEntity", fetch = FetchType.LAZY)
    private List<ProductWarehouseEntity> warehouses = new ArrayList<>();

    @Transient
    private String imageUrl;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp(source = SourceType.DB)
    private Instant updatedAt;

    public ProductEntity(UUID id, String name, String code, String brand, String image, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.image = image;
        this.category = category;
    }
}
