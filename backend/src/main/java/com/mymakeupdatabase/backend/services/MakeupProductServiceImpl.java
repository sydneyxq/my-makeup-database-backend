package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.mappers.MakeupProductMapper;
import com.mymakeupdatabase.backend.model.MakeupProductDto;
import com.mymakeupdatabase.backend.repositories.MakeupProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void saveProduct(MakeupProduct makeupProduct) {
        makeupProductRepository.save(makeupProduct);
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
    public List<MakeupProductDto> findByType(String type) {
        List<MakeupProduct> products = makeupProductRepository.findByTypeContainingIgnoreCase(type);

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
    public List<MakeupProductDto> findByPriceRange(String priceRange) {
        List<MakeupProduct> products = makeupProductRepository.findByPriceRangeContainingIgnoreCase(priceRange);

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
