package com.parqueadero.tarifas;

import java.math.BigDecimal;

public class PagoSalidaVehiculo {
	
	private BigDecimal totalPagar;
	private String placa;
	private Integer horastranscurridas;
	
	public PagoSalidaVehiculo() {
		
	}

	public PagoSalidaVehiculo(BigDecimal totalPagar, String placa, Integer horastranscurridas) {
		super();
		this.totalPagar = totalPagar;
		this.placa = placa;
		this.horastranscurridas = horastranscurridas;
	}

	public BigDecimal getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(BigDecimal totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getHorastranscurridas() {
		return horastranscurridas;
	}

	public void setHorastranscurridas(Integer horastranscurridas) {
		this.horastranscurridas = horastranscurridas;
	}
}
