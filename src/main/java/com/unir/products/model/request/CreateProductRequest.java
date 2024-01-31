package com.unir.products.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

	private Long idproducto;
	private String nombre;
	private String codigo;
	private String categoria;
	private String descripcioncorta;
	private String descripcionlarga;
	private String imagen;
	private Integer valorUnitario;
	private Integer cantidadisponible;
	private String imagenbase64;
	private Boolean borrado;
}
