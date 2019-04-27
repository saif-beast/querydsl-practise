package com.influido.poc.domains;


import com.influido.poc.dto.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double price;

    @Column(name = "quantity_left")
    private Long quantityLeft;

    @Min(value = 0)
    @Max(value = 5)
    private Float rating;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Long getQuantityLeft() {
        return quantityLeft;
    }

    public Product setQuantityLeft(Long quantityLeft) {
        this.quantityLeft = quantityLeft;
        return this;
    }

    public Float getRating() {
        return rating;
    }

    public Product setRating(Float rating) {
        this.rating = rating;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Product setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO()
                .setDeleted(product.isDeleted)
                .setId(product.id)
                .setName(product.name)
                .setPrice(product.price)
                .setQuantityLeft(product.quantityLeft)
                .setRating(product.rating);
    }
}
