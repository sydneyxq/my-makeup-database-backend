package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.model.MakeupProductDto;
import com.mymakeupdatabase.backend.repositories.MakeupProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MakeupProductServiceImplTest {

    private static final Logger logger = Logger.getLogger(MakeupProductServiceImplTest.class.getName());

    @MockitoBean
    MakeupProductRepository makeupProductRepository;

    @Autowired
    MakeupProductService makeupProductService;

    private static final MakeupProduct PRODUCT_1 = new MakeupProduct(UUID.randomUUID(),"Shadow Palette", "Eyeshadow", "dasique",
            new BigDecimal("34.87"), "Drugstore", "South Korea");
    private static final MakeupProduct PRODUCT_2 = new MakeupProduct(UUID.randomUUID(),"Munyutto Highlighter", "Highlighter", "Canmake",
            new BigDecimal("13.56"), "Drugstore", "Japan");
    private static final MakeupProduct PRODUCT_3 = new MakeupProduct(UUID.randomUUID(),"Violet Strawberry Rococo Glowy Lipgloss", "Lipgloss", "Flower Knows",
            new BigDecimal("29.80"), "Drugstore", "China");

    @Test
    void listAllProducts() {
        given(makeupProductRepository.findAll()).willReturn(List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3));

        List<MakeupProductDto> allProducts = makeupProductService.listAllProducts();

        assertEquals(3, allProducts.size());
        assertEquals("Shadow Palette", allProducts.get(0).name());
    }

    @Test
    void searchProducts() {
        given(makeupProductRepository.findByNameContainingIgnoreCase("violet")).willReturn(List.of(PRODUCT_3));

        List<MakeupProductDto> productsByName = makeupProductService.searchProducts("violet");

        assertEquals(1, productsByName.size());
        assertEquals("Violet Strawberry Rococo Glowy Lipgloss", productsByName.get(0).name());
        assertEquals("Flower Knows", productsByName.get(0).brand());
    }

    @Test
    void findByType() {
        given(makeupProductRepository.findByTypeContainingIgnoreCase("highlighter")).willReturn(List.of(PRODUCT_2));

        List<MakeupProductDto> productsByType = makeupProductService.findByType("highlighter");

        assertEquals(1, productsByType.size());
        assertEquals("Munyutto Highlighter", productsByType.get(0).name());
        assertEquals("Canmake", productsByType.get(0).brand());
    }

    @Test
    void findByBrand() {
        given(makeupProductRepository.findByBrandContainingIgnoreCase("dasique")).willReturn(List.of(PRODUCT_1));

        List<MakeupProductDto> productsByBrand = makeupProductService.findByBrand("dasique");

        assertEquals(1, productsByBrand.size());
        assertEquals("Shadow Palette", productsByBrand.get(0).name());
        assertEquals("dasique", productsByBrand.get(0).brand());
    }

    @Test
    void findByPriceRange() {
        given(makeupProductRepository.findByPriceRangeContainingIgnoreCase("drugstore")).willReturn(List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3));

        List<MakeupProductDto> productsByPriceRange = makeupProductService.findByPriceRange("drugstore");

        assertEquals(3, productsByPriceRange.size());
        assertEquals("Shadow Palette", productsByPriceRange.get(0).name());
        assertEquals("dasique", productsByPriceRange.get(0).brand());

        assertEquals("Munyutto Highlighter", productsByPriceRange.get(1).name());
        assertEquals("Canmake", productsByPriceRange.get(1).brand());

        assertEquals("Violet Strawberry Rococo Glowy Lipgloss", productsByPriceRange.get(2).name());
        assertEquals("Flower Knows", productsByPriceRange.get(2).brand());
    }

    @Test
    void findByCountry() {
        given(makeupProductRepository.findByCountryContainingIgnoreCase("South Korea")).willReturn(List.of(PRODUCT_1));

        List<MakeupProductDto> productsByCountry = makeupProductService.findByCountry("South Korea");

        assertEquals(1, productsByCountry.size());
        assertEquals("Shadow Palette", productsByCountry.get(0).name());
        assertEquals("dasique", productsByCountry.get(0).brand());
    }
}