package com.mymakeupdatabase.backend.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakeupProductCSVRecord {

    @CsvBindByName
    private Integer code;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String type;

    @CsvBindByName
    private String brand;

    @CsvBindByName
    private BigDecimal price;

    @CsvBindByName
    private String priceRange;

    @CsvBindByName
    private String country;

    @CsvBindByName
    private String mainImageUrl;

}
