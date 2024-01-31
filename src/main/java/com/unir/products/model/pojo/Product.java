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
	
	@Column(name = "categoria")
	private String categoria;

	@Column(name = "descripcioncorta")
	private String descripcioncorta;

	@Column(name = "descripcionlarga")
	private String descripcionlarga;

	@Column(name = "imagen")
	private String imagen;

	@Column(name = "valorunitario")
	private Integer valorunitario;

	@Column(name = "cantidadisponible")
	private Integer cantidadisponible;

	@Column(name = "imagenbase64")
	private String imagenbase64;
	
	@Column(name = "borrado")
	private Boolean borrado;

	public void update(ProductDto productDto) {
		this.descripcioncorta = productDto.getDescripcioncorta();
		this.descripcionlarga = productDto.getDescripcionlarga();
		this.imagen = productDto.getImagen();
		this.valorunitario = productDto.getValorunitario();
		this.cantidadisponible = productDto.getCantidadisponible();
		this.imagenbase64 = productDto.getImagenbase64();
		this.nombre = productDto.getNombre();
		this.codigo = productDto.getCodigo();
		this.categoria = productDto.getCategoria();
		this.borrado = productDto.getBorrado();
	}

}
