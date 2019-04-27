package com.influido.poc.dto;

public class ProductDTO {

    private Long id;

    private String name;

    private Double price;

    private Long quantityLeft;

    private Float rating;

    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public ProductDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductDTO setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Long getQuantityLeft() {
        return quantityLeft;
    }

    public ProductDTO setQuantityLeft(Long quantityLeft) {
        this.quantityLeft = quantityLeft;
        return this;
    }

    public Float getRating() {
        return rating;
    }

    public ProductDTO setRating(Float rating) {
        this.rating = rating;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public ProductDTO setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }
}
