package com.jl.product.util;

import com.jl.product.model.LabelType;
import com.jl.product.model.Price;
import com.jl.product.model.ReducedDressResponse;
import javafx.util.converter.BigDecimalStringConverter;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class PriceLabelConverter {
    private static final String WAS = "Was";
    private static final String NOW = "now";
    private static final String THEN = "then";
    private static final String PERC_OF = "% off";
    static final char COMMA = ',';

    public static String constructPriceLabel(String labelType, ReducedDressResponse price){

        String priceLabel = null;
        if(EnumUtils.isValidEnum(LabelType.class,labelType)) {
            if (LabelType.SHOWPERCDISCOUNT.toString().equalsIgnoreCase(labelType)) {
                if (price.getReducedPrice() != null) {
                    BigDecimal perc = (price.getReducedPrice()).divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(100)).setScale(0);
                    priceLabel = new StringBuilder(perc.toPlainString()).append(PERC_OF).append(" - ").append(NOW).append(price.getNowPrice()).toString();
                }
            } else if (LabelType.SHOWWASTHENNOW.toString().equalsIgnoreCase(labelType)) {
                StringBuilder tmpPriceLabel = new StringBuilder(WAS).append(StringUtils.SPACE).append(price.getWasPrice());
                if (StringUtils.isNotEmpty(price.getThenPrice()))
                    tmpPriceLabel.append(COMMA).append(THEN).append(StringUtils.SPACE).append(price.getThenPrice());
                tmpPriceLabel.append(COMMA).append(NOW).append(StringUtils.SPACE).append(price.getNowPrice());
                priceLabel = tmpPriceLabel.toString();
            } else {
                priceLabel = new StringBuilder(WAS).append(StringUtils.SPACE).append(price.getWasPrice()).append(COMMA).append(NOW).append(StringUtils.SPACE).append(price.getNowPrice()).toString();
            }
        }
        price.setPriceLabel(priceLabel);
        return priceLabel;

    }

}
