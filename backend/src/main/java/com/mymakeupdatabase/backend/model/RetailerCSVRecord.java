package com.mymakeupdatabase.backend.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetailerCSVRecord {

    @CsvBindByName
    private Integer productCode;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private BigDecimal price;

    @CsvBindByName
    private Integer shippingPrice;
}
