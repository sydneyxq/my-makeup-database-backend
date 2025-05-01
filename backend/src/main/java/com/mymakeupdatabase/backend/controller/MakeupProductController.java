package com.mymakeupdatabase.backend.controller;

import com.mymakeupdatabase.backend.model.MakeupProductDto;
import com.mymakeupdatabase.backend.services.MakeupProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api")
@RestController
public class MakeupProductController {

    private final MakeupProductService makeupProductService;

    public MakeupProductController(MakeupProductService makeupProductService) {
        this.makeupProductService = makeupProductService;
    }

    @GetMapping("/search")
    public List<MakeupProductDto> searchProducts(@RequestParam String query) {
        return makeupProductService.searchProducts(query);
    }

    @GetMapping("/list")
    public List<MakeupProductDto> listAllProducts() {
        return makeupProductService.listAllProducts();
    }

    @GetMapping("/type")
    public List<MakeupProductDto> findByType(@RequestParam("type") String type) {
        return makeupProductService.findByType(type);
    }

    @GetMapping("/brand")
    public List<MakeupProductDto> findByBrand(@RequestParam("brand") String brand) {
        return makeupProductService.findByBrand(brand);
    }

    @GetMapping("/price-range")
    public List<MakeupProductDto> findByPriceRange(@RequestParam("priceRange") String priceRange) {
        return makeupProductService.findByPriceRange(priceRange);
    }

    @GetMapping("/country")
    public List<MakeupProductDto> findByCountry(@RequestParam("country") String country) {
        return makeupProductService.findByCountry(country);
    }
}
