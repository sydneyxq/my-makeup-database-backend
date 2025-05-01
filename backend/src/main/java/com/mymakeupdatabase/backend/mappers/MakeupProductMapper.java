package com.mymakeupdatabase.backend.mappers;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.model.MakeupProductDto;
import org.mapstruct.Mapper;

@Mapper(uses = {ShadeMapper.class, RetailMapper.class})
public interface MakeupProductMapper {

    MakeupProductDto makeupProductToMakeupProductDto(MakeupProduct makeupProduct);
    
}
