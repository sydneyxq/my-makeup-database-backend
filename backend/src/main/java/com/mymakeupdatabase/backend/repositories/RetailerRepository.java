package com.mymakeupdatabase.backend.repositories;

import com.mymakeupdatabase.backend.entities.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RetailerRepository extends JpaRepository <Retailer, UUID> {
}
