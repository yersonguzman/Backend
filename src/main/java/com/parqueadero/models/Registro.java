package com.parqueadero.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table (name = "Registro")
public class Registro {
	
	public Registro() {
	}

	public Registro(long idRegistro, String placa, Vehiculo vehiculoHistorico, @NotNull LocalDateTime fechaIngreso,
			LocalDateTime fechaSalida, BigDecimal totalPago) {
		super();
		this.idRegistro = idRegistro;
		this.placa = placa;
	//	this.vehiculoHistorico = vehiculoHistorico;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.totalPago = totalPago;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_registro")
	private long idRegistro;
	
		
	@Column(name = "fk_placa", length = 7)
	private String placa;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "fk_placa", insertable = false, updatable = false)
// 	private Vehiculo vehiculoHistorico;
	
	@NotNull
	@Column(name = "fecha_ingreso")
	private LocalDateTime fechaIngreso;
	
	@Column(name = "fecha_salida")
	private LocalDateTime fechaSalida;
	
	
	@Column (name = "total_pago")
	private BigDecimal totalPago;

	public long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	//public Vehiculo getVehiculoHistorico() {
	//	return vehiculoHistorico;
	//}

	//public void setVehiculoHistorico(Vehiculo vehiculoHistorico) {
		//this.vehiculoHistorico = vehiculoHistorico;
	//}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public BigDecimal getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(BigDecimal totalPago) {
		this.totalPago = totalPago;
	}

	
}

