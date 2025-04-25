package com.mymakeupdatabase.backend.repositories;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MakeupProductRepository extends JpaRepository<MakeupProduct, UUID> {
    List<MakeupProduct> findByNameContainingIgnoreCase(String query);

    List<MakeupProduct> findByTypeContainingIgnoreCase(String type);

    List<MakeupProduct> findByBrandContainingIgnoreCase(String brand);

    List<MakeupProduct> findByPriceRangeContainingIgnoreCase(String priceRange);

    List<MakeupProduct> findByCountryContainingIgnoreCase(String country);
}
