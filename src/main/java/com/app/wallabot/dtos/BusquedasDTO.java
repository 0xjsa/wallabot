package com.app.wallabot.dtos;

public class BusquedasDTO {

	private Long id;
	private String url;
	private String descripcion;

	public BusquedasDTO(Long id, String url, String descripcion) {
		this.id = id;
		this.url = url;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
