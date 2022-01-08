package com.app.wallabot.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.wallabot.dtos.ArticulosDTO;

@Entity
@Table(name = "ARTICULOS")
public class Articulos {

	private String idArticulo;
	private String titulo;
	private String descripcion;
	private double precio;
	private Busquedas busqueda;

	public Articulos() {
	}

	public Articulos(String idArticulo, String titulo, String descripcion, double precio, Busquedas busqueda) {
		this.idArticulo = idArticulo;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.busqueda = busqueda;
	}

	@Id
	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	@Column(length = 50)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Column(length = 650)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToOne
	@JoinColumn(name = "ID_BUSQUEDA", nullable = false)
	public Busquedas getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(Busquedas busqueda) {
		this.busqueda = busqueda;
	}

	public ArticulosDTO toDTO() {
		return new ArticulosDTO(this.idArticulo, this.titulo, this.descripcion, this.precio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArticulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulos other = (Articulos) obj;
		return Objects.equals(idArticulo, other.idArticulo);
	}

}
