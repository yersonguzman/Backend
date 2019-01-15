package com.parqueadero.constants;

import java.math.BigDecimal;

public class Constants {

	public static final BigDecimal valor_hora_carro = new BigDecimal (1000);
	public static final BigDecimal valor_dia_carro = new BigDecimal (8000);
	public static final BigDecimal valor_hora_moto = new BigDecimal(500);
	public static final BigDecimal valor_dia_moto = new BigDecimal(4000);
	public static final int capacidad_carro = 20;
	public static final int capacidad_moto = 10;
	public static final int alto_cilindraje = 500;
	public static final BigDecimal excedente_alto_cilindraje = new BigDecimal (2000);
	public static final int dia_comienza = 9;
	public static final int dia_termina = 24;
	
	public static final String regla_de_placa ="A";
	public static final String sin_espacio = "No hay espacio disponible, se encuentra lleno";
	public static final String dia_no_permitido = "No puede ingresar porque no está en un dia habil";
	public static final String no_hay_registro = "registro no encontrado";
	public static final String no_hay_carro = "no se encuentra vehiculo en el parqueadero";
	public static final String no_hay_DatoRegistro ="no se encuentra registro del vehiculo";
	public static final String vehiculo_parqueado = "el vehiculo se encuentra parqueado";
	
}
