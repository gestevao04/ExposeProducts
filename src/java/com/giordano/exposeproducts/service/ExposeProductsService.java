package com.giordano.exposeproducts.service;

import com.giordano.exposeproducts.dto.request.ListProductReqDTO;
import com.giordano.exposeproducts.entity.ProductEntity;
import com.giordano.exposeproducts.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExposeProductsService {
    @Autowired
    private ProductRepository productRepository;

    public Boolean isAvalibleInStock(String prodId) {
        return productRepository.existsByIdAndAvalibleStockGreaterThan(prodId, 0);
    }

    public Optional<ProductEntity> getProduct(String prodId) {
        return productRepository.findById(prodId);
    }

    public List<ProductEntity> getProductList(ListProductReqDTO listProductReqDTO) {
        listProductReqDTO = treatMinMaxValues(listProductReqDTO);

        if (listProductReqDTO.getPriceAsc() && listProductReqDTO.getCategory().isEmpty()) {
            return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(
                    listProductReqDTO.getMinPrice(), listProductReqDTO.getMaxPrice());
        } else if (listProductReqDTO.getPriceAsc() && !listProductReqDTO.getCategory().isEmpty()) {
            return productRepository.findByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(
                    listProductReqDTO.getCategory(), listProductReqDTO.getMinPrice(), listProductReqDTO.getMaxPrice());
        } else if (listProductReqDTO.getPriceDesc() && listProductReqDTO.getCategory().isEmpty()) {
            return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceDesc(
                    listProductReqDTO.getMinPrice(), listProductReqDTO.getMaxPrice());
        } else if (listProductReqDTO.getPriceDesc() && !listProductReqDTO.getCategory().isEmpty()) {
            return productRepository.findByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceDesc(
                    listProductReqDTO.getCategory(), listProductReqDTO.getMinPrice(), listProductReqDTO.getMaxPrice());
        } else if (listProductReqDTO.getCategory().isEmpty()) {
            // No price ordering defaults to ordering by most popular product
            return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqualOrderByQttSoldDesc(
                    listProductReqDTO.getMinPrice(), listProductReqDTO.getMaxPrice());
        } else {
            // No price ordering defaults to ordering by most popular product
            return productRepository.findByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqualOrderByQttSoldDesc(
                    listProductReqDTO.getCategory(), listProductReqDTO.getMinPrice(), listProductReqDTO.getMaxPrice());
        }
    }

    private ListProductReqDTO treatMinMaxValues(ListProductReqDTO listProductReqDTO) {
        if (listProductReqDTO.getMinPrice() == null || listProductReqDTO.getMinPrice() < 0) {
            listProductReqDTO.setMinPrice(0.0);
        }
        if (listProductReqDTO.getMaxPrice() == null || listProductReqDTO.getMaxPrice() == 0) {
            listProductReqDTO.setMaxPrice(Double.MAX_VALUE);
        }
        return listProductReqDTO;
    }
}
