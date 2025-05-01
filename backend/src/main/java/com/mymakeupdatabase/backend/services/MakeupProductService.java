package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.model.MakeupProductDto;

import java.util.List;

public interface MakeupProductService {

    List<MakeupProductDto> listAllProducts();

    List<MakeupProductDto> searchProducts(String query);

    List<MakeupProductDto> findByType(String typeString);

    List<MakeupProductDto> findByBrand(String brand);

    List<MakeupProductDto> findByPriceRange(String stringPriceRange);

    List<MakeupProductDto> findByCountry(String country);

}
