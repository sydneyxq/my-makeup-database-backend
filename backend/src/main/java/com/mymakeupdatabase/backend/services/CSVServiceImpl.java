package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.model.MakeupProductCSVRecord;
import com.mymakeupdatabase.backend.model.RetailerCSVRecord;
import com.mymakeupdatabase.backend.model.ShadeCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class CSVServiceImpl implements CSVService {

    @Override
    public List<MakeupProductCSVRecord> convertMakeupProductCSV(File makeupProductCSVFile) {
        try {
            return new CsvToBeanBuilder<MakeupProductCSVRecord>(new FileReader(makeupProductCSVFile))
                    .withType(MakeupProductCSVRecord.class)
                    .build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ShadeCSVRecord> convertShadeCSV(File shadeCSVFile) {
        try {
            return new CsvToBeanBuilder<ShadeCSVRecord>(new FileReader(shadeCSVFile))
                    .withType(ShadeCSVRecord.class)
                    .build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RetailerCSVRecord> convertRetailerCSV(File retailerCSVFile) {
        try {
            return new CsvToBeanBuilder<RetailerCSVRecord>(new FileReader(retailerCSVFile))
                    .withType(RetailerCSVRecord.class)
                    .build().parse();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
