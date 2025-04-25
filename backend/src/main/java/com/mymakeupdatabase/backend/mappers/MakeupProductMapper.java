package com.mymakeupdatabase.backend.mappers;

import com.mymakeupdatabase.backend.entities.MakeupProduct;
import com.mymakeupdatabase.backend.model.MakeupProductDto;
import org.mapstruct.Mapper;

@Mapper
public interface MakeupProductMapper {

    MakeupProduct makeupProductDtoToMakeupProduct(MakeupProductDto dto);

    MakeupProductDto makeupProductToMakeupProductDto(MakeupProduct makeupProduct);
    
}
