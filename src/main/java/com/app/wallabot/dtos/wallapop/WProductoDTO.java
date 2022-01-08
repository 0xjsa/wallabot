package com.app.wallabot.dtos.wallapop;

import com.app.wallabot.entities.Articulos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WProductoDTO {

	private String id;
	private String description;
	private double price;
	private String title;

	public WProductoDTO() {
	}

	public WProductoDTO(String id, String description, double price, String title) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Articulos toArticulos() {
		Articulos articulo = new Articulos();
		articulo.setIdArticulo(this.id);
		articulo.setTitulo(this.title);
		articulo.setDescripcion(this.description);
		articulo.setPrecio(this.price);
		return articulo;
	}

}
