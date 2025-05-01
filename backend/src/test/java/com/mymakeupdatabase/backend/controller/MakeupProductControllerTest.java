package com.mymakeupdatabase.backend.controller;

import com.mymakeupdatabase.backend.model.MakeupProductDto;
import com.mymakeupdatabase.backend.model.RetailerDto;
import com.mymakeupdatabase.backend.model.ShadeDto;
import com.mymakeupdatabase.backend.services.MakeupProductService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static com.mymakeupdatabase.backend.constants.PriceRange.DRUGSTORE;
import static com.mymakeupdatabase.backend.constants.Type.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class MakeupProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MakeupProductService makeupProductService;

    private static final ShadeDto SUGAR_BROWNIE_SHADE = new ShadeDto("#01 Sugar Brownie", "#ffb68f", "test1_url");
    private static final List<ShadeDto> SHADES_1 = List.of(SUGAR_BROWNIE_SHADE);
    private static final ShadeDto MOONLIGHT_GEM = new ShadeDto("#01 Moonlight Gem", "#ffbeb3", "test2_url");
    private static final List<ShadeDto> SHADES_2 = List.of(MOONLIGHT_GEM);
    private static final ShadeDto STRAWBERRY_MACARON = new ShadeDto("#G01 Strawberry Macaron", "#ffbeb3", "test3_url");
    private static final List<ShadeDto> SHADES_3 = List.of(STRAWBERRY_MACARON);

    private static final RetailerDto SUKOSHI_MART_1 = new RetailerDto("Sukoshi Mart", new BigDecimal("35.99"), 65);
    private static final RetailerDto OOMOMO_1 = new RetailerDto("OOMOMO", new BigDecimal("38.64"), 150);
    private static final List<RetailerDto> RETAILERS_1 = List.of(SUKOSHI_MART_1, OOMOMO_1);
    private static final RetailerDto STYLEVANA_2 = new RetailerDto("Stylevana", new BigDecimal("12.89"), 68);
    private static final List<RetailerDto> RETAILERS_2 = List.of(STYLEVANA_2);
    private static final RetailerDto KIOKII_3 = new RetailerDto("kiokii and...", new BigDecimal("24.99"), 75);
    private static final List<RetailerDto> RETAILERS_3 = List.of(KIOKII_3);

    private static final MakeupProductDto PRODUCT_1 = new MakeupProductDto("Shadow Palette", EYESHADOW, "dasique",
            new BigDecimal("34.87"), DRUGSTORE, "South Korea", SHADES_1, RETAILERS_1, "test1_url");
    private static final MakeupProductDto PRODUCT_2 = new MakeupProductDto("Munyutto Highlighter", HIGHLIGHTER, "Canmake",
            new BigDecimal("13.56"), DRUGSTORE, "Japan", SHADES_2, RETAILERS_2, "test2_url");
    private static final MakeupProductDto PRODUCT_3 = new MakeupProductDto("Violet Strawberry Rococo Glowy Lipgloss", LIPGLOSS, "Flower Knows",
            new BigDecimal("29.80"), DRUGSTORE, "China", SHADES_3, RETAILERS_3, "test3_url");

    @Test
    void testListAllProducts_Success() throws Exception {
        given(makeupProductService.listAllProducts()).willReturn(List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3));

        mockMvc.perform(get("/api/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void testSearchProducts_Success() throws Exception {
        given(makeupProductService.searchProducts("munyutto highlighter")).willReturn(List.of(PRODUCT_2));

        mockMvc.perform(get("/api/search")
                .accept(MediaType.APPLICATION_JSON)
                        .param("query", "munyutto highlighter"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void testFindProductsByType_Success() throws Exception {
        given(makeupProductService.findByType("eyeshadow")).willReturn(List.of(PRODUCT_1));

        mockMvc.perform(get("/api/type")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("type", "eyeshadow"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void testFindProductsByBrand_Success() throws Exception {
        given(makeupProductService.findByBrand("Flower knows")).willReturn(List.of(PRODUCT_3));

        mockMvc.perform(get("/api/brand")
                .accept(MediaType.APPLICATION_JSON)
                .param("brand", "Flower knows"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }

    @Test
    void testFindProductsByPriceRange_Success() throws Exception {
        given(makeupProductService.findByPriceRange("Drugstore")).willReturn(List.of(PRODUCT_1, PRODUCT_2, PRODUCT_3));

        mockMvc.perform(get("/api/price-range")
                .accept(MediaType.APPLICATION_JSON)
                .param("priceRange", "Drugstore"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void testFindProductsByCountry_Success() throws Exception {
        given(makeupProductService.findByCountry("China")).willReturn(List.of(PRODUCT_3));

        mockMvc.perform(get("/api/country")
                .accept(MediaType.APPLICATION_JSON)
                .param("country", "China"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }

}