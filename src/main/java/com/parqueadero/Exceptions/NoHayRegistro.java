package com.parqueadero.Exceptions;

public class NoHayRegistro extends RuntimeException {
		
	public NoHayRegistro (String mensaje) {
		super(mensaje);
		
	}
}
