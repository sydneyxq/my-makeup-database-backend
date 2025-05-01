package com.mymakeupdatabase.backend;

import com.mymakeupdatabase.backend.constants.PriceRange;
import com.mymakeupdatabase.backend.constants.Type;
import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.entities.Retailer;
import com.mymakeupdatabase.backend.entities.Shade;
import com.mymakeupdatabase.backend.model.MakeupProductCSVRecord;
import com.mymakeupdatabase.backend.model.RetailerCSVRecord;
import com.mymakeupdatabase.backend.model.ShadeCSVRecord;
import com.mymakeupdatabase.backend.repositories.MakeupProductRepository;
import com.mymakeupdatabase.backend.repositories.RetailerRepository;
import com.mymakeupdatabase.backend.repositories.ShadeRepository;
import com.mymakeupdatabase.backend.services.CSVService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CSVDataLoader implements CommandLineRunner {

    private final CSVService csvService;
    private final MakeupProductRepository makeupProductRepository;
    private final ShadeRepository shadeRepository;
    private final RetailerRepository retailerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadMakeupProductCSVData();
    }

    private void loadMakeupProductCSVData() throws FileNotFoundException {
        makeupProductRepository.deleteAll();

        File makeupProductFile = ResourceUtils.getFile("classpath:csv-data/makeup-products.csv");
        File shadeFile = ResourceUtils.getFile("classpath:csv-data/shades.csv");
        File retailerFile = ResourceUtils.getFile("classpath:csv-data/retailers.csv");

        List<MakeupProductCSVRecord> makeupProductCSVRecordList = csvService.convertMakeupProductCSV(makeupProductFile);
        List<ShadeCSVRecord> shadeCSVRecordList = csvService.convertShadeCSV(shadeFile);
        List<RetailerCSVRecord> retailerCSVRecordList = csvService.convertRetailerCSV(retailerFile);

        makeupProductCSVRecordList.forEach(makeupProductCSVRecord -> {
            Integer code = makeupProductCSVRecord.getCode();
            MakeupProduct makeupProduct = new MakeupProduct();

            Type type = switch (makeupProductCSVRecord.getType()) {
                case "Foundation" -> Type.FOUNDATION;
                case "Concealer" -> Type.CONCEALER;
                case "BB Cream" -> Type.BBCREAM;
                case "Powder" -> Type.POWDER;
                case "Blush" -> Type.BLUSH;
                case "Highlighter" -> Type.HIGHLIGHTER;
                case "Contour" -> Type.CONTOUR;
                case "Bronzer" -> Type.BRONZER;
                case "Lip Tint" -> Type.LIPTINT;
                case "Lipstick" -> Type.LIPSTICK;
                case "Lip Gloss" -> Type.LIPGLOSS;
                case "Lip Liner" -> Type.LIPLINER;
                case "Eyeshadow" -> Type.EYESHADOW;
                case "Eyebrow" -> Type.EYEBROW;
                case "Eyeliner" -> Type.EYELINER;
                case "Mascara" -> Type.MASCARA;
                default -> throw new IllegalStateException("Unexpected value: " + makeupProductCSVRecord.getType());
            };

            PriceRange priceRange = switch (makeupProductCSVRecord.getPriceRange()) {
                case "Drugstore" -> PriceRange.DRUGSTORE;
                case "Highend" -> PriceRange.HIGHEND;
                default -> null;
            };

            makeupProduct.setCode(makeupProductCSVRecord.getCode());
            makeupProduct.setName(makeupProductCSVRecord.getName());
            makeupProduct.setType(type);
            makeupProduct.setBrand(makeupProductCSVRecord.getBrand());
            makeupProduct.setPrice(makeupProductCSVRecord.getPrice());
            makeupProduct.setPriceRange(priceRange);
            makeupProduct.setCountry(makeupProductCSVRecord.getCountry());
            makeupProduct.setMainImageUrl(makeupProductCSVRecord.getMainImageUrl());

            makeupProductRepository.save(makeupProduct);

            shadeCSVRecordList.forEach(shadeCSVRecord -> {
                if (Objects.equals(shadeCSVRecord.getProductCode(), code)) {
                    shadeRepository.save(Shade.builder()
                            .name(shadeCSVRecord.getName())
                            .hexcode(shadeCSVRecord.getHexcode())
                            .imageUrl(shadeCSVRecord.getImageUrl())
                            .makeupProduct(makeupProduct)
                            .build());
                }
            });

            retailerCSVRecordList.forEach(retailerCSVRecord -> {
                if (Objects.equals(retailerCSVRecord.getProductCode(), code)) {
                    retailerRepository.save(Retailer.builder()
                            .name(retailerCSVRecord.getName())
                            .price(retailerCSVRecord.getPrice())
                            .shippingPrice(retailerCSVRecord.getShippingPrice())
                            .makeupProduct(makeupProduct)
                            .build());
                }
            });
        });
    }
}
