package com.mymakeupdatabase.backend.controller;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.model.MakeupProductDto;
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
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class MakeupProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MakeupProductService makeupProductService;

    private static final MakeupProduct DATABASE_PRODUCT = new MakeupProduct(UUID.randomUUID(),"Shadow Palette", "Eyeshadow", "dasique",
            new BigDecimal("34.87"), "Drugstore", "South Korea");
    private static final MakeupProductDto PRODUCT_1 = new MakeupProductDto("Shadow Palette", "Eyeshadow", "dasique",
            new BigDecimal("34.87"), "Drugstore", "South Korea");
    private static final MakeupProductDto PRODUCT_2 = new MakeupProductDto("Munyutto Highlighter", "Highlighter", "Canmake",
            new BigDecimal("13.56"), "Drugstore", "Japan");
    private static final MakeupProductDto PRODUCT_3 = new MakeupProductDto("Violet Strawberry Rococo Glowy Lipgloss", "Lipgloss", "Flower Knows",
            new BigDecimal("29.80"), "Drugstore", "China");

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

        mockMvc.perform(get("/api/pricerange")
                .accept(MediaType.APPLICATION_JSON)
                .param("price-range", "Drugstore"))
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