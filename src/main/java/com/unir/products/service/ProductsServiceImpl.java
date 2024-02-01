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
	public List<Product> getProducts(String nombre, String categoria, String descripcioncorta, String descripcionlarga, Double valorunitario, Integer indValorUnitario,  Boolean indEliminado){
		//return null;
		if (StringUtils.hasLength(nombre) || StringUtils.hasLength(categoria) || StringUtils.hasLength(descripcioncorta) ||
				StringUtils.hasLength(descripcionlarga) || valorunitario!=null || indEliminado != null) {
			return repository.search(nombre, categoria, descripcioncorta, descripcionlarga, valorunitario, indValorUnitario,  indEliminado);
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
			dto.setIndEliminado(true);
			repository.save(dto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Product createProduct(CreateProductRequest request) {

		if (request != null && StringUtils.hasLength(request.getCodigo().trim())
				&& StringUtils.hasLength(request.getNombre().trim())
				&& StringUtils.hasLength(request.getCategoria().trim()) && request.getDescripcioncorta() != null
				&& StringUtils.hasLength(request.getDescripcionlarga().trim())  && StringUtils.hasLength(request.getImagen().trim())
				&& request.getValorunitario()!=null  && request.getCantidadisponible()!=null
				&& request.getIndEliminado()!=null  && StringUtils.hasLength(request.getImagenbase64().trim()) ) {

			Product product = Product.builder().codigo(request.getCodigo()).nombre(request.getNombre()).categoria(request.getCategoria())
					.descripcioncorta(request.getDescripcioncorta()).descripcionlarga(request.getDescripcionlarga())
					.imagen(request.getImagen()).valorunitario(request.getValorunitario()).cantidadisponible(request.getCantidadisponible())
					.indEliminado(request.getIndEliminado()).imagenbase64(request.getImagenbase64()).build();

			log.info("Producto nuevo: {}", product );

			return repository.save(product);
		} else {
			return null;
		}
	}

	@Override
	public Product updateProduct(String productId, String updateRequest) {
		//PATCH se implementa en este caso mediante Merge Patch: https://datatracker.ietf.org/doc/html/rfc7386
		Product product = repository.getById(Long.valueOf(productId));
		if (product != null) {
			try {
				JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updateRequest));
				JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(product)));
				Product patched = objectMapper.treeToValue(target, Product.class);
				repository.save(patched);
				return patched;
			} catch (JsonProcessingException | JsonPatchException e) {
				log.error("Error updating product {}", productId, e);
				return null;
			}
		} else {
			return null;
		}
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
