package com.unir.products.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproducto;
	
	@Column(name = "nombre", unique = true)
	private String nombre;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "visible")
	private Boolean visible;

	public void update(ProductDto productDto) {
		this.nombre = productDto.getName();
		this.codigo = productDto.getCountry();
		this.description = productDto.getDescription();
		this.visible = productDto.getVisible();
	}

}
