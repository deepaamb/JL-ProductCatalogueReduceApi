package com.jl.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchProductCategoryResponse {

    @JsonProperty
    private List<Product> products;



    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
