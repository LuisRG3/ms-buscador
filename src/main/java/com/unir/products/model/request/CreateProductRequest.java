package com.unir.products.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

	@NotNull(message = "`codigo` cannot be null")
	@NotEmpty(message = "`codigo` cannot be empty")
	private String codigo;
	@NotNull(message = "`nombre` cannot be null")
	@NotEmpty(message = "`nombre` cannot be empty")
	private String nombre;
	@NotNull(message = "`categoria` cannot be null")
	@NotEmpty(message = "`categoria` cannot be empty")
	private String categoria;
	@NotNull(message = "`descripcioncorta` cannot be null")
	@NotEmpty(message = "`descripcioncorta` cannot be empty")
	private String descripcioncorta;
	@NotNull(message = "`descripcionlarga` cannot be null")
	@NotEmpty(message = "`descripcionlarga` cannot be empty")
	private String descripcionlarga;
	@NotNull(message = "`imagen` cannot be null")
	@NotEmpty(message = "`imagen` cannot be empty")
	private String imagen;
	@NotNull(message = "`valorunitario` cannot be null")
	@NotEmpty(message = "`valorunitario` cannot be empty")
	private Double valorunitario;
	@NotNull(message = "`cantidadisponible` cannot be null")
	@NotEmpty(message = "`cantidadisponible` cannot be empty")
	private Integer cantidadisponible;
	@NotNull(message = "`indEliminado` cannot be null")
	@NotEmpty(message = "`indEliminado` cannot be empty")
	private Boolean indEliminado;
	@NotNull(message = "`imagenbase64` cannot be null")
	@NotEmpty(message = "`imagenbase64` cannot be empty")
	private String imagenbase64;
}
