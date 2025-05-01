package com.mymakeupdatabase.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Retailer {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @NotNull
    private String name;
    private BigDecimal price;
    private int shippingPrice;

    @ManyToOne
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    private MakeupProduct makeupProduct;

    public Retailer(String name, BigDecimal price, int shippingPrice) {
        this.name = name;
        this.price = price;
        this.shippingPrice = shippingPrice;
    }
}
