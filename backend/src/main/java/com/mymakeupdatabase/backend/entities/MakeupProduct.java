package com.mymakeupdatabase.backend.entities;

import com.mymakeupdatabase.backend.constants.PriceRange;
import com.mymakeupdatabase.backend.constants.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MakeupProduct {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @Column(unique = true, nullable = false)
    private Integer code;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;

    @NotNull
    private String brand;

    @NotNull
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private PriceRange priceRange;

    @NotNull
    private String country;

    @OneToMany(mappedBy = "makeupProduct", cascade = CascadeType.ALL)
    private List<Shade> shades;

    @OneToMany(mappedBy = "makeupProduct", cascade = CascadeType.ALL)
    private List<Retailer> retailers;

    @NotNull
    private String mainImageUrl;

    public MakeupProduct(String name, Type type, String brand, BigDecimal price, PriceRange priceRange,
                         String country, List<Shade> shades, List<Retailer> retailers, String mainImageUrl) {
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.priceRange = priceRange;
        this.country = country;
        this.shades = shades;
        this.retailers = retailers;
        this.mainImageUrl = mainImageUrl;
    }
}
