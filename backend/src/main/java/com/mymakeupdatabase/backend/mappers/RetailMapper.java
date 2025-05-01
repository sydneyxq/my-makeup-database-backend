package com.mymakeupdatabase.backend.mappers;

import com.mymakeupdatabase.backend.entities.Retailer;
import com.mymakeupdatabase.backend.model.RetailerDto;
import org.mapstruct.Mapper;

@Mapper
public interface RetailMapper {
    RetailerDto retailerToRetailerDto(Retailer retailer);
}
