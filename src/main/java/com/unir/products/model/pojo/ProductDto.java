package com.unir.products.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDto {

	private Long idproducto;
	private String nombre;
	private String codigo;
	private String categoria;
	private String descripcioncorta;
	private String descripcionlarga;
	private String imagen;
	private Integer valorunitario;
	private Integer cantidadisponible;
	private String imagenbase64;
	private Boolean borrado;

}
