package com.parqueadero.Exceptions;

public class ParqueoLleno extends RuntimeException {
	
	public ParqueoLleno(String mensaje) {
		super (mensaje);
		
	}

}
