package com.jl.product.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.jl.product.model.LabelType;
import com.jl.product.model.ReducedDressResponse;
import com.jl.product.model.SearchProductCategoryResponse;
import com.jl.product.restClient.ExternalServiceConstants;
import com.jl.product.restClient.RestTemplateClient;
import com.jl.product.service.ProductDressService;
import com.jl.product.util.ColorToHEXLookup;
import com.jl.product.util.PriceLabelConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductCategorySearchResource {

@Autowired
    ProductDressService productDressService;

    @GetMapping("/catalogue/products/dresses/reduced")
    public  List<ReducedDressResponse>  retrieveDressesReduced(@RequestParam(required = false,defaultValue = "ShowWasNow") LabelType labelType) {

        return productDressService.retrieveReducedDresses(labelType);
    }
}
