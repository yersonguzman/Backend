package com.parqueadero.modelsTest;

import java.text.ParseException;
import java.time.LocalDateTime;

import com.parqueadero.models.Vehiculo;

public class VehiculoTestDataBuilder {

	private String placa;
	private Integer cilindraje;
	private String tipo;
	private LocalDateTime fechaIngreso;

	public VehiculoTestDataBuilder() throws ParseException {

		this.placa = "LAT605";
		this.cilindraje = 100;
		this.tipo = "carro";
	}

	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public VehiculoTestDataBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}
	public VehiculoTestDataBuilder confecha(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public Vehiculo build() {
		return new Vehiculo(placa, cilindraje, tipo, fechaIngreso);
	}

}
