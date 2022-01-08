package com.app.wallabot.dtos;

public class ArticulosDTO {

	private String idArticulo;
	private String titulo;
	private String descripcion;
	private double precio;

	public ArticulosDTO() {
	}

	public ArticulosDTO(String idArticulo, String titulo, String descripcion, double precio) {
		this.idArticulo = idArticulo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
