package com.jl.product.service;

import com.jl.product.model.LabelType;
import com.jl.product.model.ReducedDressResponse;

import java.util.List;


public interface ProductDressService {

    List<ReducedDressResponse> retrieveReducedDresses(LabelType LabelType);
}
