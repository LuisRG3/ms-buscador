package com.unir.products.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {

	private String codigo;
	private String nombre;
	private String categoria;
	private String descripcioncorta;
	private String descripcionlarga;
	private String imagen;
	private Double valorunitario;
	private Integer cantidadisponible;
	private Boolean indEliminado;
	private String imagenbase64;
}
