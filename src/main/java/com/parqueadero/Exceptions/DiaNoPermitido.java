package com.parqueadero.Exceptions;

public class DiaNoPermitido extends RuntimeException {

	
	private static final long serialVersionUID = 1424660503018027796L;

	public DiaNoPermitido(String mensaje) {
		super(mensaje);
	}
}
