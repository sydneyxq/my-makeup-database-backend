package com.mymakeupdatabase.backend.repositories;

import com.mymakeupdatabase.backend.entities.Shade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShadeRepository extends JpaRepository<Shade, UUID> {
}
