package com.nasa.stocktrack.infra.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "id.productEntity")
    private List<ProductWarehouseEntity> warehouses = new ArrayList<>();

    public ProductEntity(UUID id, String name, String code, String brand, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.category = category;
    }
}
