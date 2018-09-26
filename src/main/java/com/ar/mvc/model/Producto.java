package com.ar.mvc.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 *
 */

@Entity
@Table(name = "PRODUCTO")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "el campo no puede ser nulo")
	private String nombreProducto;

	@Positive(message="el stock tiene que ser positivo")
	private int stockMinimo;

	
	private Oferta oferta = new Oferta();
	

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "producto")
	public Oferta getOferta() {
		return oferta;
	}

	@Id
	@Column(name = "NOMPROD")
	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	@Column(name = "stkMinimo")
	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	

	/*
	 * public void setOfertas(List<Oferta> ofertas) { this.ofertas = ofertas;
	 * 
	 * }
	 * 
	 * @OneToMany(
	 * mappedBy="producto",targetEntity=Oferta.class,fetch=FetchType.EAGER,
	 * cascade=CascadeType.ALL) public List<Oferta> getOfertas(){ return
	 * ofertas; }
	 * 
	 */

}
