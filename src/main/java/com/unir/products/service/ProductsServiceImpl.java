package com.unir.products.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.products.data.ProductRepository;
import com.unir.products.model.pojo.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.products.model.pojo.Product;
import com.unir.products.model.request.CreateProductRequest;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Product> getProducts(String name, String categoria, String description, Boolean visible) {
		//return null;
		if (StringUtils.hasLength(name) || StringUtils.hasLength(categoria) || StringUtils.hasLength(description)
				|| visible != null) {
			return repository.search(name, categoria, description, visible);
		}

		List<Product> products = repository.getProducts();
		return products.isEmpty() ? null : products;
	}

	@Override
	public Product getProduct(String productId) {
		try {
			Long id = Long.parseLong(productId);
			return repository.getById(id);
		} catch (NumberFormatException e) {
			// Manejar la excepción si el formato del ID no es un número válido
			throw new IllegalArgumentException("El formato del ID no es válido", e);
		}
	}

	@Override
	public Boolean removeProduct(String productId) {

		try {
			Product dto=repository.getById(Long.valueOf(productId));
			dto.setBorrado(true);
			repository.save(dto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Product createProduct(Product request) {
		return repository.save(request);
	}

	@Override
	public Product updateProduct(String productId, String updateRequest) {
		return null;
	}

	@Override
	public Product updateProduct(String productId, ProductDto updateRequest) {
		Product product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			product.update(updateRequest);
			repository.save(product);
			return product;
		} else {
			return null;
		}
	}


}
