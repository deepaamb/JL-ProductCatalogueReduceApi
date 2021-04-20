package com.jl.product.util;


import com.jl.product.exception.InvalidArgumentException;
import com.jl.product.model.LabelType;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class StringToLabelEnumConverter implements Converter<String, LabelType> {
    @Override
    public LabelType convert(String source) {
        try {
            if(EnumUtils.isValidEnum(LabelType.class,source.toUpperCase())) {
                LabelType labelType = EnumUtils.getEnum(LabelType.class, source.toUpperCase());
                return labelType;
            }else{
                throw new InvalidArgumentException("Label Type needs to be one of "+EnumUtils.getEnumList(LabelType.class));
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Label Type needs to be one of ");
        }

    }
}
