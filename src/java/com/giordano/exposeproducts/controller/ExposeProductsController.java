package com.giordano.exposeproducts.controller;

import com.giordano.exposeproducts.dto.request.ListProductReqDTO;
import com.giordano.exposeproducts.entity.ProductEntity;
import com.giordano.exposeproducts.mapper.ProductMapper;
import com.giordano.exposeproducts.service.ExposeProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/expose/")
public class ExposeProductsController {

	@Autowired
	private ExposeProductsService exposeProductsService;

	@ApiOperation(value = "Delivers a list of products", response = Boolean.class)
	@GetMapping(path = "/product/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> productList(@RequestBody ListProductReqDTO listProductReqDTO) {
		List<ProductEntity> prodList = exposeProductsService.getProductList(listProductReqDTO);
		if (prodList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ProductMapper.listToDto(prodList), HttpStatus.OK);
	}

	@ApiOperation(value = "Delivers a single product by ID", response = Boolean.class)
	@GetMapping(path = "/product/{prodId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> product(@PathVariable String prodId) {
		Optional<ProductEntity> prod = exposeProductsService.getProduct(prodId);
		if (!prod.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(ProductMapper.toDto(prod.get()), HttpStatus.OK);
	}

	@ApiOperation(value = "Finds if product avalible in stock by ID", response = Boolean.class)
	@GetMapping(path = "/isAvalibleInStock/{prodId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> isAvalibleInStock(@PathVariable String prodId) {
		return new ResponseEntity<>(exposeProductsService.isAvalibleInStock(prodId), HttpStatus.OK);
	}
}