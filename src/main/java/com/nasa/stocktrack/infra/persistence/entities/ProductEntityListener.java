package com.nasa.stocktrack.infra.persistence.entities;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ProductEntityListener {

    @PostLoad
    @PrePersist
    void setImageUrl(ProductEntity productEntity) {
        if(productEntity.getImage() == null) {
            return;
        }

        String imageUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("products/images/")
                .path(productEntity.getImage())
                .toUriString();


        productEntity.setImageUrl(imageUrl);
    }

}
