package com.giordano.exposeproducts.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ProductDTO {
    String id;
    String name;
    String category;
    String url;
    String iconUrl;
    Double price;
    Integer qttSold;
    Integer avalibleStock;
    LocalDateTime createdDate;
}
