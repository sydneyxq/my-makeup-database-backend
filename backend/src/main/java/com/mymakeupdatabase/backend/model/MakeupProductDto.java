package com.mymakeupdatabase.backend.model;

import java.math.BigDecimal;

public record MakeupProductDto(String name, String type, String brand,
                               BigDecimal price, String priceRange, String country) {
}
