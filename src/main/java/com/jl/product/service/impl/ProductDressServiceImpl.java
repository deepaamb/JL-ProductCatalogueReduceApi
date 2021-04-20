package com.jl.product.service.impl;

import com.jl.product.exception.RecordNotFoundException;
import com.jl.product.model.LabelType;
import com.jl.product.model.Product;
import com.jl.product.model.ReducedDressResponse;
import com.jl.product.model.SearchProductCategoryResponse;
import com.jl.product.restClient.ExternalServiceConstants;
import com.jl.product.restClient.RestTemplateClient;
import com.jl.product.service.ProductDressService;
import com.jl.product.util.ColorToHEXLookup;
import com.jl.product.util.PriceLabelConverter;
import javafx.util.converter.BigDecimalStringConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDressServiceImpl implements ProductDressService {

    @Autowired
    RestTemplateClient restTemplateClient;

    /**
     * Method makes a call to the JL api to retrieve all dress and retrieves only the one's reduced.
     * @param labelType
     * @return
     */
    @Override
    public List<ReducedDressResponse> retrieveReducedDresses(LabelType labelType) {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("q","dresses");
        params.add("key","AIzaSyDD_6O5gUgC4tRW5f9kxC0_76XRC8W7_mI");
        List<ReducedDressResponse> responses = new ArrayList<>();
        final SearchProductCategoryResponse searchProductCategoryResponse = restTemplateClient.getForEntity(SearchProductCategoryResponse.class, ExternalServiceConstants.JL_API_SEARCH_URL, params);
        if(searchProductCategoryResponse !=null && !CollectionUtils.isEmpty(searchProductCategoryResponse.getProducts())){
        searchProductCategoryResponse.getProducts().stream().forEach(product -> {
                if(product.getPrice() != null && StringUtils.isNotEmpty(product.getPrice().getWas()) && StringUtils.isNotEmpty(product.getPrice().getNow())) {
                    BigDecimal wasPrice = new BigDecimalStringConverter().fromString(product.getPrice().getWas());
                    BigDecimal nowPrice = new BigDecimalStringConverter().fromString(product.getPrice().getNow());
                    if(wasPrice.compareTo(nowPrice) >0) {
                        BigDecimal reducedPrice = wasPrice.subtract(nowPrice);
                        responses.add(constructResponse(labelType, product,reducedPrice));
                    }
                }
            });
        }
        if(responses.size() == 0)
            throw new RecordNotFoundException("There are no reduced dresses at the moment");
        return responses;
    }

    private ReducedDressResponse constructResponse(LabelType labelType,Product product,BigDecimal reducedPrice){
        ReducedDressResponse reducedDressResponse = new ReducedDressResponse();
        reducedDressResponse.setProductId(product.getProductId());
        reducedDressResponse.setTitle(product.getTitle());
        reducedDressResponse.setCurrency(product.getPrice().getCurrency());
        reducedDressResponse.setNowPrice(product.getPrice().getNow());
        reducedDressResponse.setWasPrice(product.getPrice().getWas());
        reducedDressResponse.setThenPrice(StringUtils.isNotEmpty(product.getPrice().getThen2()) ? product.getPrice().getThen2() : product.getPrice().getThen1());
        reducedDressResponse.setReducedPrice(reducedPrice);
        product.getColorSwatches().stream().forEach(colorSwatch -> {
            final ReducedDressResponse.ColorSwatch reducecolorSwatch = new ReducedDressResponse.ColorSwatch();
            final String hexaColor = ColorToHEXLookup.getHexaColor(colorSwatch.getBasicColor());
            reducecolorSwatch.setRgbColor(hexaColor);
            reducecolorSwatch.setColor(colorSwatch.getColor());
            reducecolorSwatch.setSkuId(colorSwatch.getSkuId());
            reducedDressResponse.getColorSwatches().add(reducecolorSwatch);

        });
        PriceLabelConverter.constructPriceLabel(labelType.toString(),reducedDressResponse);
        return reducedDressResponse;
    }
}
