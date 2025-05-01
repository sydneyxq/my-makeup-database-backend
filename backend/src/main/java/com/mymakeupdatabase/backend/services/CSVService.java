package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.model.MakeupProductCSVRecord;
import com.mymakeupdatabase.backend.model.RetailerCSVRecord;
import com.mymakeupdatabase.backend.model.ShadeCSVRecord;

import java.io.File;
import java.util.List;

public interface CSVService {
    List<MakeupProductCSVRecord> convertMakeupProductCSV(File makeupProductCSVFile);

    List<ShadeCSVRecord> convertShadeCSV(File shadeCSVFile);

    List<RetailerCSVRecord> convertRetailerCSV(File retailerCSVFile);
}
