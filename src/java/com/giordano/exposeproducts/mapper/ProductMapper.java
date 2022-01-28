package com.giordano.exposeproducts.mapper;

import com.giordano.exposeproducts.dto.response.ProductDTO;
import com.giordano.exposeproducts.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static ProductDTO toDto (ProductEntity entity) {
        ProductDTO ret = new ProductDTO();
        ret.setAvalibleStock(entity.getAvalibleStock());
        ret.setCategory(entity.getCategory());
        ret.setCreatedDate(entity.getCreatedDate());
        ret.setIconUrl(entity.getIconUrl());
        ret.setId(entity.getId());
        ret.setName(entity.getName());
        ret.setPrice(entity.getPrice());
        ret.setQttSold(entity.getQttSold());
        ret.setUrl(entity.getUrl());
        return ret;
    }

    public static List<ProductDTO> listToDto (List<ProductEntity> entList) {
        List<ProductDTO> retList = new ArrayList<>();
        for (ProductEntity ent : entList) {
            retList.add(toDto(ent));
        }
        return retList;
    }
}
