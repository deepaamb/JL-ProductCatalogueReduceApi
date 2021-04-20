package com.jl.product.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ColorToHEXLookup {

    static final Map<String,String> COLOR_CODES;
    static {
        Map<String,String> map = new HashMap();
        map.put("magenta", "#FF00FF");
        map.put("pink", "#FFAFAF");
        map.put("green", "#00FF00");
        map.put("black", "#000000");
        map.put("yellow", "#FFFF00");
        map.put("cyan", "#00FFFF");
        map.put("dark gray", "#404040");
        map.put("red", "#FF0000");
        map.put("orange", "#FFC800");
        map.put("gray", "#808080");
        map.put("white", "#FFFFFF");
        map.put("blue", "#0000FF");
        map.put("darkgray", "#404040");
        map.put("light gray", "#C0C0C0");
        map.put("lightgray", "#C0C0C0");
        COLOR_CODES = Collections.unmodifiableMap(map);
    }
   public static String getHexaColor(String colorName) {
        return COLOR_CODES.getOrDefault(colorName.toLowerCase(), "");
    }
}
