package com.parqueadero.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Vehiculo")
public class Vehiculo {

	public Vehiculo() {
	}

	public Vehiculo(String placa, Integer cilindraje, @NotNull String tipo, LocalDateTime fechaIngreso) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipo = tipo;
		this.fechaIngreso = fechaIngreso;

	}

	@Id
	@Column(name = "placa", length = 7)
	private String placa;

	@Column(name = "Cilindraje")
	private Integer cilindraje;

	@NotNull
	@Column(name = "tipo")
	private String tipo;

	@NotNull
	@Column(name = "fecha_ingreso")
	private LocalDateTime fechaIngreso;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

}
