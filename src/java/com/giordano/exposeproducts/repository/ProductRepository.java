package com.giordano.exposeproducts.repository;

import com.giordano.exposeproducts.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, String> {
    Boolean existsByIdAndAvalibleStockGreaterThan(String id, Integer stock);

    List<ProductEntity> findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(
            Double minPrice, Double maxPrice);

    List<ProductEntity> findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceDesc(
            Double minPrice, Double maxPrice);

    List<ProductEntity> findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByQttSoldDesc(
            Double minPrice, Double maxPrice);

    List<ProductEntity> findByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(
            String category, Double minPrice, Double maxPrice);

    List<ProductEntity> findByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceDesc(
            String category, Double minPrice, Double maxPrice);

    List<ProductEntity> findByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqualOrderByQttSoldDesc(
            String category, Double minPrice, Double maxPrice);
}
