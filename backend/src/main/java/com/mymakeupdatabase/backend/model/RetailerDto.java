package com.mymakeupdatabase.backend.model;

import java.math.BigDecimal;

public record RetailerDto(String name, BigDecimal price, int shippingPrice) {
}
