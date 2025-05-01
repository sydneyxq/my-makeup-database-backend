package com.mymakeupdatabase.backend.services;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.entities.Retailer;
import com.mymakeupdatabase.backend.entities.Shade;
import com.mymakeupdatabase.backend.model.MakeupProductDto;
import com.mymakeupdatabase.backend.repositories.MakeupProductRepository;
import com.mymakeupdatabase.backend.repositories.RetailerRepository;
import com.mymakeupdatabase.backend.repositories.ShadeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import static com.mymakeupdatabase.backend.constants.PriceRange.DRUGSTORE;
import static com.mymakeupdatabase.backend.constants.Type.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MakeupProductServiceImplTest {

    private static final Logger logger = Logger.getLogger(MakeupProductServiceImplTest.class.getName());

    @MockitoBean
    MakeupProductRepository makeupProductRepository;

    @MockitoBean
    ShadeRepository shadeRepository;

    @MockitoBean
    RetailerRepository retailerRepository;

    @Autowired
    MakeupProductService makeupProductService;

    private static final Shade SUGAR_BROWNIE_SHADE = new Shade("#01 Sugar Brownie", "#ffb68f", "test1_url");
    private static final List<Shade> SHADES_1 = List.of(SUGAR_BROWNIE_SHADE);
    private static final Shade MOONLIGHT_GEM = new Shade("#01 Moonlight Gem", "#ffbeb3", "test2_url");
    private static final List<Shade> SHADES_2 = List.of(MOONLIGHT_GEM);
    private static final Shade STRAWBERRY_MACARON = new Shade("#G01 Strawberry Macaron", "#ffbeb3", "test3_url");
    private static final List<Shade> SHADES_3 = List.of(STRAWBERRY_MACARON);

    private static final Retailer SUKOSHI_MART_1 = new Retailer("Sukoshi Mart", new BigDecimal("35.99"), 65);
    private static final Retailer OOMOMO_1 = new Retailer("OOMOMO", new BigDecimal("38.64"), 150);
    private static final List<Retailer> RETAILERS_1 = List.of(SUKOSHI_MART_1, OOMOMO_1);
    private static final Retailer STYLEVANA_2 = new Retailer("Stylevana", new BigDecimal("12.89"), 68);
    private static final List<Retailer> RETAILERS_2 = List.of(STYLEVANA_2);
    private static final Retailer KIOKII_3 = new Retailer("kiokii and...", new BigDecimal("24.99"), 75);
    private static final List<Retailer> RETAILERS_3 = List.of(KIOKII_3);

    private static final MakeupProduct PRODUCT_1 = new MakeupProduct("Shadow Palette", EYESHADOW, "dasique",
            new BigDecimal("34.87"), DRUGSTORE, "South Korea", SHADES_1, RETAILERS_1, "test_url_1");
    private static final MakeupProduct PRODUCT_2 = new MakeupProduct("Munyutto Highlighter", HIGHLIGHTER, "Canmake",
            new BigDecimal("13.56"), DRUGSTORE, "Japan", SHADES_2, RETAILERS_2, "test_url_1");
    private static final MakeupProduct PRODUCT_3 = new MakeupProduct("Violet Strawberry Rococo Glowy Lipgloss", LIPGLOSS, "Flower Knows",
            new BigDecimal("29.80"), DRUGSTORE, "China", SHADES_3, RETAILERS_3, "test_url_1");

    @Test
    void listAllProducts() {
        given(makeupProductRepository.findAll()).willReturn(List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3));
        given(shadeRepository.findAll()).willReturn(List.of(SUGAR_BROWNIE_SHADE, MOONLIGHT_GEM, STRAWBERRY_MACARON));
        given(retailerRepository.findAll()).willReturn(List.of(SUKOSHI_MART_1, OOMOMO_1, STYLEVANA_2, KIOKII_3));

        List<MakeupProductDto> allProducts = makeupProductService.listAllProducts();

        assertEquals(3, allProducts.size());
        assertEquals("Shadow Palette", allProducts.get(0).name());
        assertEquals("#01 Sugar Brownie", allProducts.get(0).shades().get(0).name());
        assertEquals(68, allProducts.get(1).retailers().get(0).shippingPrice());
    }

    @Test
    void searchProducts() {
        given(makeupProductRepository.findByNameContainingIgnoreCase("violet")).willReturn(List.of(PRODUCT_3));
        given(shadeRepository.findAll()).willReturn(List.of(STRAWBERRY_MACARON));
        given(retailerRepository.findAll()).willReturn(List.of(KIOKII_3));

        List<MakeupProductDto> productsByName = makeupProductService.searchProducts("violet");

        assertEquals(1, productsByName.size());
        assertEquals("Violet Strawberry Rococo Glowy Lipgloss", productsByName.get(0).name());
        assertEquals("Flower Knows", productsByName.get(0).brand());
        assertEquals("#ffbeb3", productsByName.get(0).shades().get(0).hexcode());
        assertEquals(new BigDecimal("24.99"), productsByName.get(0).retailers().get(0).price());
    }

    @Test
    void findByType() {
        given(makeupProductRepository.findByType(HIGHLIGHTER)).willReturn(List.of(PRODUCT_2));
        given(shadeRepository.findAll()).willReturn(List.of(MOONLIGHT_GEM));
        given(retailerRepository.findAll()).willReturn(List.of(STYLEVANA_2));

        List<MakeupProductDto> productsByType = makeupProductService.findByType("highlighter");

        assertEquals(1, productsByType.size());
        assertEquals("Munyutto Highlighter", productsByType.get(0).name());
        assertEquals("Canmake", productsByType.get(0).brand());
        assertEquals("#01 Moonlight Gem", productsByType.get(0).shades().get(0).name());
        assertEquals("Stylevana", productsByType.get(0).retailers().get(0).name());
    }

    @Test
    void findByBrand() {
        given(makeupProductRepository.findByBrandContainingIgnoreCase("dasique")).willReturn(List.of(PRODUCT_1));
        given(shadeRepository.findAll()).willReturn(List.of(SUGAR_BROWNIE_SHADE));
        given(retailerRepository.findAll()).willReturn(List.of(SUKOSHI_MART_1, OOMOMO_1));

        List<MakeupProductDto> productsByBrand = makeupProductService.findByBrand("dasique");

        assertEquals(1, productsByBrand.size());
        assertEquals("Shadow Palette", productsByBrand.get(0).name());
        assertEquals("dasique", productsByBrand.get(0).brand());
        assertEquals("#01 Sugar Brownie", productsByBrand.get(0).shades().get(0).name());
        assertEquals(new BigDecimal("38.64"), productsByBrand.get(0).retailers().get(1).price());
    }

    @Test
    void findByPriceRange() {
        given(makeupProductRepository.findByPriceRange(DRUGSTORE)).willReturn(List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3));
        given(shadeRepository.findAll()).willReturn(List.of(SUGAR_BROWNIE_SHADE, MOONLIGHT_GEM, STRAWBERRY_MACARON));
        given(retailerRepository.findAll()).willReturn(List.of(SUKOSHI_MART_1, OOMOMO_1, STYLEVANA_2, KIOKII_3));

        List<MakeupProductDto> productsByPriceRange = makeupProductService.findByPriceRange("drugstore");

        assertEquals(3, productsByPriceRange.size());
        assertEquals("Shadow Palette", productsByPriceRange.get(0).name());
        assertEquals("dasique", productsByPriceRange.get(0).brand());

        assertEquals("Munyutto Highlighter", productsByPriceRange.get(1).name());
        assertEquals("Canmake", productsByPriceRange.get(1).brand());
        assertEquals("#01 Moonlight Gem", productsByPriceRange.get(1).shades().get(0).name());

        assertEquals("Violet Strawberry Rococo Glowy Lipgloss", productsByPriceRange.get(2).name());
        assertEquals("Flower Knows", productsByPriceRange.get(2).brand());
        assertEquals(75, productsByPriceRange.get(2).retailers().get(0).shippingPrice());
    }

    @Test
    void findByCountry() {
        given(makeupProductRepository.findByCountryContainingIgnoreCase("South Korea")).willReturn(List.of(PRODUCT_1));
        given(shadeRepository.findAll()).willReturn(List.of(MOONLIGHT_GEM));
        given(retailerRepository.findAll()).willReturn(List.of(SUKOSHI_MART_1, OOMOMO_1));

        List<MakeupProductDto> productsByCountry = makeupProductService.findByCountry("South Korea");

        assertEquals(1, productsByCountry.size());
        assertEquals("Shadow Palette", productsByCountry.get(0).name());
        assertEquals("dasique", productsByCountry.get(0).brand());
        assertEquals("#ffb68f", productsByCountry.get(0).shades().get(0).hexcode());
        assertEquals(65, productsByCountry.get(0).retailers().get(0).shippingPrice());
    }
}