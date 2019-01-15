package com.parqueadero.Exceptions;

public class DiaNoPermitido extends RuntimeException {

	public DiaNoPermitido(String mensaje) {
		super(mensaje);
	}
}
