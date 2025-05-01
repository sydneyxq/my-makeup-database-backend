package com.mymakeupdatabase.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shade {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    private UUID id;

    @NotNull
    private String name;
    private String hexcode;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    MakeupProduct makeupProduct;

    public Shade(String name, String hexcode, String imageUrl) {
        this.name = name;
        this.hexcode = hexcode;
        this.imageUrl = imageUrl;
    }
}
