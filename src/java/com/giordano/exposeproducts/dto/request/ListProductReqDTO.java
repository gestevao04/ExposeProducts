package com.giordano.exposeproducts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ListProductReqDTO {
    String category;
    Double maxPrice;
    Double minPrice;
    Boolean priceDesc;
    Boolean priceAsc;
}
