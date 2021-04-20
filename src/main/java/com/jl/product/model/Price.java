
package com.jl.product.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "was",
    "then1",
    "then2",
    "now",
    "uom",
    "currency"
})

public class Price {

    @JsonProperty("was")
    private String was;
    @JsonProperty("then1")
    private String then1;
    @JsonProperty("then2")
    private String then2;
    @JsonProperty("now")
    private String now;
    @JsonProperty("uom")
    private String uom;
    @JsonProperty("currency")
    private String currency;


    @JsonProperty("was")
    public String getWas() {
        return was;
    }

    @JsonProperty("was")
    public void setWas(String was) {
        this.was = was;
    }

    @JsonProperty("then1")
    public String getThen1() {
        return then1;
    }

    @JsonProperty("then1")
    public void setThen1(String then1) {
        this.then1 = then1;
    }

    @JsonProperty("then2")
    public String getThen2() {
        return then2;
    }

    @JsonProperty("then2")
    public void setThen2(String then2) {
        this.then2 = then2;
    }

    @JsonProperty("now")
    public String getNow() {
        return now;
    }

    @JsonProperty("now")
    public void setNow(JsonNode nowNode) {
        if(nowNode.isTextual()){
            this.now = nowNode.asText();
        }
    }

    @JsonProperty("uom")
    public String getUom() {
        return uom;
    }

    @JsonProperty("uom")
    public void setUom(String uom) {
        this.uom = uom;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

public static class NowPrice{
        @JsonProperty
    String from;
        @JsonProperty
    String to;

        public NowPrice(String from,String to){
            this.from = from;
        this.to=to;
        }
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

}

