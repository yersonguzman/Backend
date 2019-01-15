package com.parqueadero.Exceptions;

public class NoseEncuentraVehiculo extends RuntimeException {

	public NoseEncuentraVehiculo (String mensaje){
		super(mensaje);
	}
}
