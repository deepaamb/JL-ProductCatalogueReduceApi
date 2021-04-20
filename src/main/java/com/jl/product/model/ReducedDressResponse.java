package com.jl.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.util.converter.BigDecimalStringConverter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class ReducedDressResponse {
    @JsonProperty("productId")
    private String productId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("colorSwatches")
    private List<ColorSwatch> colorSwatches = new ArrayList<>();
    @JsonProperty("nowPrice")
    private String nowPrice;
    @JsonProperty("priceLabel")
    private String priceLabel;

    @JsonIgnore
    private String wasPrice;
    @JsonIgnore
    private String currency;
    @JsonIgnore
    private String thenPrice;
    @JsonIgnore
    private BigDecimal reducedPrice;

    public BigDecimal getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(BigDecimal reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ColorSwatch> getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(List<ColorSwatch> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }

    public String getWasPrice() {
        return wasPrice;
    }

    public void setWasPrice(String wasPrice) {
        this.wasPrice = priceFormat(wasPrice);
    }

    public String getThenPrice() {
        return thenPrice;
    }

    public void setThenPrice(String thenPrice) {
        this.thenPrice = priceFormat(thenPrice);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPriceStr) {

         this.nowPrice = priceFormat(nowPriceStr);
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

    public static class ColorSwatch{
        @JsonProperty("color")
        private String color;
        @JsonProperty("rgbColor")
        private String rgbColor;
        @JsonProperty("skuId")
        private String skuId;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getRgbColor() {
            return rgbColor;
        }

        public void setRgbColor(String rgbColor) {
            this.rgbColor = rgbColor;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }
    }

    private String priceFormat(String price){
        Currency currency = Currency.getInstance(getCurrency());
        String formattedPrice = null;
        if(StringUtils.isNotEmpty(price)){
            BigDecimal priceBg = new BigDecimalStringConverter().fromString(price);
            if(priceBg.compareTo(new BigDecimal(10)) == -1){
                formattedPrice = currency.getSymbol()+price;
            }else {
                priceBg = priceBg.setScale(0,BigDecimal.ROUND_HALF_DOWN);
                formattedPrice = currency.getSymbol()+priceBg;
            }
        }
        return formattedPrice;
    }
}
