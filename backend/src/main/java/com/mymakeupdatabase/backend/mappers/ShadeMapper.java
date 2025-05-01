package com.mymakeupdatabase.backend.mappers;

import com.mymakeupdatabase.backend.entities.Shade;
import com.mymakeupdatabase.backend.model.ShadeDto;
import org.mapstruct.Mapper;

@Mapper
public interface ShadeMapper {
    ShadeDto shadeToShadeDto(Shade shade);
}
