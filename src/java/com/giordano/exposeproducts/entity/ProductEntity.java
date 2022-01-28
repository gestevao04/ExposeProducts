package com.giordano.exposeproducts.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
//@Table(schema = "COMPANY", name = "TB_PRODUCT")
@Getter @Setter
public class ProductEntity {
    @Id
    @Column(name = "ID")
    String id;

    @Column(name = "NAME")
    String name;

    @Column(name = "CATEGORY")
    String category;

    @Column(name = "URL")
    String url;

    @Column(name = "ICON_URL")
    String iconUrl;

    @Column(name = "PRICE")
    Double price;

    @Column(name = "QUANTITY_SOLD")
    Integer qttSold;

    @Column(name = "AVALIBLE_STOCK")
    Integer avalibleStock;

    @Column(name = "CREATED_DATE")
    LocalDateTime createdDate;
}
