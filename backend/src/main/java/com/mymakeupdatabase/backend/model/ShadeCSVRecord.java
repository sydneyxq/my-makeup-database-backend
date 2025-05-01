package com.mymakeupdatabase.backend.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShadeCSVRecord {

    @CsvBindByName
    private Integer productCode;

    @CsvBindByName
    private String name;

    @CsvBindByName
    private String hexcode;

    @CsvBindByName
    private String imageUrl;
}
