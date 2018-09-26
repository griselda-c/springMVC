package com.ar.mvc.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codCli", nullable=false)
	private int id;
	
	@Column(name="nomCli")
	private String nomCli;
	
	private String localidad;
	
	private int compraMaxima;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="codCliContacto") //clave foranea
	@JsonIgnore
	private Cliente codCliContacto;

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public String getNomCli() {
		return nomCli;
	}

	public void setNomCli(String nombre) {
		this.nomCli = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	@Column(name="LOCALIDAD")
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getCompraMaxima() {
		return compraMaxima;
	}

	@Column(name="COMPRAMAXIMA")
	public void setCompraMaxima(int compraMaxima) {
		this.compraMaxima = compraMaxima;
	}

	
	public Cliente getCodCliContacto() {
		return codCliContacto;
	}

	public void setCodCliContacto(Cliente clienteContacto) {
		this.codCliContacto = clienteContacto;
	}
	
	
	public String toString(){
		String contacto = " CONTACTO: ";
		
		if(codCliContacto == null){
			contacto = "Contacto es null";
		}else{
			contacto = "Contacto: "+ this.codCliContacto.getId();
		}
		
		String cliente = "Nombre: "+this.nomCli + " Localidad "+this.localidad+ " Compra Maxima: "+this.compraMaxima+contacto;
		
		return cliente;
	}
	
	
	

}
