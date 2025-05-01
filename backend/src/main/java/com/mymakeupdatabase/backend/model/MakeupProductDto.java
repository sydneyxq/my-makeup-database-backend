package com.mymakeupdatabase.backend.model;

import com.mymakeupdatabase.backend.constants.PriceRange;
import com.mymakeupdatabase.backend.constants.Type;

import java.math.BigDecimal;
import java.util.List;

public record MakeupProductDto(String name, Type type, String brand,
                               BigDecimal price, PriceRange priceRange,
                               String country, List<ShadeDto> shades, List<RetailerDto> retailers, String mainImageUrl) {
}
