package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.constants.PriceRange;
import com.mymakeupdatabase.backend.constants.Type;
import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.mappers.MakeupProductMapper;
import com.mymakeupdatabase.backend.model.MakeupProductDto;
import com.mymakeupdatabase.backend.repositories.MakeupProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mymakeupdatabase.backend.constants.EnumUtils.fromStringIgnoreCase;

@Slf4j
@Service
public class MakeupProductServiceImpl implements MakeupProductService {

    private final MakeupProductRepository makeupProductRepository;

    private final MakeupProductMapper makeupProductMapper;

    public MakeupProductServiceImpl(MakeupProductRepository makeupProductRepository, MakeupProductMapper makeupProductMapper) {
        this.makeupProductRepository = makeupProductRepository;
        this.makeupProductMapper = makeupProductMapper;
    }

    @Override
    public List<MakeupProductDto> listAllProducts() {
        List<MakeupProduct> allProducts = makeupProductRepository.findAll();

        return allProducts
                .stream()
                .map(makeupProductMapper::makeupProductToMakeupProductDto)
                .toList();
    }

    @Override
    public List<MakeupProductDto> searchProducts(String query) {
        List<MakeupProduct> products = makeupProductRepository.findByNameContainingIgnoreCase(query);

        return products
                .stream()
                .map(makeupProductMapper::makeupProductToMakeupProductDto)
                .toList();
    }

    @Override
    public List<MakeupProductDto> findByType(String typeString) {

        Type type = fromStringIgnoreCase(Type.class, typeString);

        List<MakeupProduct> products = makeupProductRepository.findByType(type);

        return products
                .stream()
                .map(makeupProductMapper::makeupProductToMakeupProductDto)
                .toList();
    }

    @Override
    public List<MakeupProductDto> findByBrand(String brand) {
        List<MakeupProduct> products = makeupProductRepository.findByBrandContainingIgnoreCase(brand);

        return products
                .stream()
                .map(makeupProductMapper::makeupProductToMakeupProductDto)
                .toList();
    }

    @Override
    public List<MakeupProductDto> findByPriceRange(String stringPriceRange) {

        PriceRange priceRange = fromStringIgnoreCase(PriceRange.class, stringPriceRange);

        List<MakeupProduct> products = makeupProductRepository.findByPriceRange(priceRange);

        return products
                .stream()
                .map(makeupProductMapper::makeupProductToMakeupProductDto)
                .toList();
    }

    @Override
    public List<MakeupProductDto> findByCountry(String country) {
        List<MakeupProduct> products = makeupProductRepository.findByCountryContainingIgnoreCase(country);

        return products
                .stream()
                .map(makeupProductMapper::makeupProductToMakeupProductDto)
                .toList();
    }
}
