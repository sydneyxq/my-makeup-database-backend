package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.model.MakeupProductDto;

import java.util.List;

public interface MakeupProductService {

    void saveProduct(MakeupProduct makeupProduct);

    List<MakeupProductDto> listAllProducts();

    List<MakeupProductDto> searchProducts(String query);

    List<MakeupProductDto> findByType(String type);

    List<MakeupProductDto> findByBrand(String brand);

    List<MakeupProductDto> findByPriceRange(String priceRange);

    List<MakeupProductDto> findByCountry(String country);

}
